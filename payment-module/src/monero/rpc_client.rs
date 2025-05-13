use std::process::Stdio;
use std::time::Duration;
use monero_rpc::{RpcAuthentication, RpcClientBuilder, WalletClient};
use tokio::process::Command;
use tokio::sync::oneshot;
use tokio::time::sleep;
use tracing::{error, info};

pub async fn build_wallet() -> anyhow::Result<(WalletClient)> {
    let rpc = RpcClientBuilder::new()
        .rpc_authentication(RpcAuthentication::Credentials {
            username: std::env::var("RPC_USR")?,
            password: std::env::var("RPC_PASS")?,
        })
        .build(&std::env::var("MONERO_RPC")?)?
        .wallet();

    Ok(rpc)
}

pub async fn launch_wallet(ready: oneshot::Sender<()>) {
    loop {
        info!("starting monero-wallet-rpc.exe …");

        let mut child = Command::new(r#"C:\Program Files\Monero GUI Wallet\monero-wallet-rpc.exe"#)
            .args([
                "--wallet-file", "serverwallet",
                "--password", "",
                "--rpc-bind-port", "18083",
                "--rpc-login", "rpcuser:rpcpass",
                "--daemon-address", "opennode.xmr-tw.org:18089",
            ])
            .stdin(Stdio::null())
            .stdout(Stdio::null())
            .stderr(Stdio::piped())
            .spawn()
            .expect("failed to spawn wallet-rpc");

        let status = child.wait().await.expect("wallet-rpc died");
        error!("wallet-rpc exited with {status}; restarting in 5 s…");
        sleep(Duration::from_secs(5)).await;
    }
}

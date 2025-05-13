use std::env;
use tonic::transport::Server;
use std::net::SocketAddr;

use dotenv::dotenv;
mod grpc;
use grpc::transaction_service::create_payment;

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    dotenv().ok();


    let addr: SocketAddr = env::var("SERVER_ADDRESS").unwrap().parse()?;
    println!("TransactionService listening on {}", addr);

    Server::builder()
        .add_service(create_payment())
        .serve(addr)
        .await?;

    Ok(())
}

fn main() -> Result<(), Box<dyn std::error::Error>> {
    let manifest_dir = std::env::var("CARGO_MANIFEST_DIR")?;
    let proto_root = std::path::Path::new(&manifest_dir).join("..").join("proto");

    tonic_build::configure()
        .build_server(true)
        .build_client(true)
        .compile_protos(
            &[proto_root.join("transaction_service.proto")
                .to_str()
                .expect("Proto path should be valid UTF-8")], &[proto_root.to_str().unwrap()],
        )?;
    Ok(())
}

fn main() -> Result<(), Box<dyn std::error::Error>> {
    // Budujemy ścieżki absolutne względnie do katalogu crate'a
    let manifest_dir = std::env::var("CARGO_MANIFEST_DIR")?;
    // Załóżmy, że folder proto leży w ../proto względem katalogu z Cargo.toml
    let proto_root = std::path::Path::new(&manifest_dir).join("..").join("proto");

    tonic_build::configure()
        .build_server(true)
        .build_client(true)
        .compile_protos(
            // ① pełna ścieżka do pliku .proto
            &[proto_root.join("transaction_service.proto")
                .to_str()
                .expect("Proto path should be valid UTF-8")], &[proto_root.to_str().unwrap()],
        )?;
    Ok(())
}
use tonic::{Request, Response, Status};

pub mod proto {
    tonic::include_proto!(
        "pl.bartus.jakub.master.thesis.transactionsystem"
    );
}

use proto::{
    transaction_service_server::{TransactionService, TransactionServiceServer},
    TransactionRequest, PaymentAddressResponse,TransactionStatus
};

#[derive(Default)]
pub struct MyTransactionService;

#[tonic::async_trait]
impl TransactionService for MyTransactionService {
    async fn create_transaction(
        &self,
        request: Request<TransactionRequest>,
    ) -> Result<Response<PaymentAddressResponse>, Status> {
        let req = request.into_inner();

        let resp = PaymentAddressResponse {
            server_wallet_address: "chuj".into(),
            price_in_xmr: "777".into(),
            coins: req.coins,
            status: TransactionStatus::Processing.into(),
            ..Default::default()
        };
        Ok(Response::new(resp))
    }
}

pub fn create_payment() -> TransactionServiceServer<MyTransactionService> {
    TransactionServiceServer::new(MyTransactionService::default())
}
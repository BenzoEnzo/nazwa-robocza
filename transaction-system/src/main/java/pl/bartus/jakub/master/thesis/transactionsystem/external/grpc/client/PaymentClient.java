package pl.bartus.jakub.master.thesis.transactionsystem.external.grpc.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartus.jakub.master.thesis.transactionsystem.TransactionServiceGrpc;
import pl.bartus.jakub.master.thesis.transactionsystem.TransactionServiceOuterClass;

@Service
@RequiredArgsConstructor
public class PaymentClient {

    private final TransactionServiceGrpc.TransactionServiceBlockingStub stub;

    public TransactionServiceOuterClass.PaymentAddressResponse createTransaction(TransactionServiceOuterClass.TransactionRequest req){
        return stub.createTransaction(req);
    }
}
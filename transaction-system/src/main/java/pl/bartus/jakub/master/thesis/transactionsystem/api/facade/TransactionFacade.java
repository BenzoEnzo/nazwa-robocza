package pl.bartus.jakub.master.thesis.transactionsystem.api.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartus.jakub.master.thesis.transactionsystem.api.TransactionApi;
import pl.bartus.jakub.master.thesis.transactionsystem.api.dto.PaymentAddressDTO;
import pl.bartus.jakub.master.thesis.transactionsystem.api.dto.TransactionDTO;
import pl.bartus.jakub.master.thesis.transactionsystem.api.mapper.TransactionMapper;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.service.TransactionService;
import pl.bartus.jakub.master.thesis.transactionsystem.external.keycloak.user.service.UserAuthService;
import pl.bartus.jakub.master.thesis.transactionsystem.external.grpc.client.PaymentClient;
import pl.bartus.jakub.master.thesis.transactionsystem.external.grpc.proto.TransactionServiceOuterClass;

@Service
@RequiredArgsConstructor
class TransactionFacade implements TransactionApi {
    private final PaymentClient paymentClient;
    private final TransactionMapper transactionMapper;
    private final UserAuthService userAuthService;
    private final TransactionService transactionService;

    @Override
    public PaymentAddressDTO createTransaction(TransactionDTO request){
        TransactionDTO persistedDTO = transactionMapper.mapToDto(
                transactionService.create(
                        transactionMapper.mapToEntity(request,userAuthService))
        );

        TransactionServiceOuterClass.PaymentAddressResponse response = paymentClient.createTransaction(transactionMapper.mapToGrpcReq(persistedDTO));

        return transactionMapper.mapToGrpcResp(response);
    }
}

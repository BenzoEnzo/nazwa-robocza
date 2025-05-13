package pl.bartus.jakub.master.thesis.transactionsystem.api.facade;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartus.jakub.master.thesis.transactionsystem.TransactionServiceOuterClass;
import pl.bartus.jakub.master.thesis.transactionsystem.api.TransactionApi;
import pl.bartus.jakub.master.thesis.transactionsystem.api.dto.PaymentAddressDTO;
import pl.bartus.jakub.master.thesis.transactionsystem.api.dto.TransactionDTO;
import pl.bartus.jakub.master.thesis.transactionsystem.api.mapper.TransactionMapper;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.entity.Transaction;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.enumerated.TransactionStatus;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.service.TransactionService;
import pl.bartus.jakub.master.thesis.transactionsystem.external.keycloak.user.service.UserAuthService;
import pl.bartus.jakub.master.thesis.transactionsystem.external.grpc.client.PaymentClient;

@Service
@RequiredArgsConstructor
class TransactionFacade implements TransactionApi {
    private final PaymentClient paymentClient;
    private final TransactionMapper transactionMapper;
    private final UserAuthService userAuthService;
    private final TransactionService transactionService;

    @Override
    @Transactional
    public PaymentAddressDTO createTransaction(TransactionDTO request) {
        Transaction managed = transactionService.create(
                transactionMapper.mapToEntity(request, userAuthService));

        TransactionDTO persistedDTO = transactionMapper.mapToDto(managed);

        try {

            TransactionServiceOuterClass.PaymentAddressResponse response = paymentClient.createTransaction(transactionMapper.mapToGrpcReq(persistedDTO));
            PaymentAddressDTO paymentDTO = transactionMapper.mapToGrpcResp(response);
            managed.setStatus(paymentDTO.status());

            return paymentDTO;

        } catch (Exception e) {

            managed.setStatus(TransactionStatus.FAILED);
            return null;

        }
    }
}

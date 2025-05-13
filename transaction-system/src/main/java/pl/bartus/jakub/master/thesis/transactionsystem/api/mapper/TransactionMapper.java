package pl.bartus.jakub.master.thesis.transactionsystem.api.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.bartus.jakub.master.thesis.transactionsystem.TransactionServiceOuterClass;
import pl.bartus.jakub.master.thesis.transactionsystem.api.dto.PaymentAddressDTO;
import pl.bartus.jakub.master.thesis.transactionsystem.api.dto.TransactionDTO;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.entity.Transaction;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.enumerated.TransactionStatus;
import pl.bartus.jakub.master.thesis.transactionsystem.external.keycloak.user.service.UserAuthService;

@Mapper(componentModel="spring",uses =  {TransactionStatus.class})
public interface TransactionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "userId",
            expression = "java(userAuthService.getUserId())")
    Transaction mapToEntity(TransactionDTO dto, @Context UserAuthService userAuthService);

    @Mapping(target = "transactionId", source = "id")
    TransactionDTO mapToDto(Transaction entity);

    @Mapping(target = "transactionId",
            source = "dto.transactionId")
    @Mapping(target = "status",
            expression = "java(TransactionServiceOuterClass.TransactionStatus.valueOf(dto.getStatus().name()))")
    @Mapping(target = "xmrAmount",
            source = "dto.xmrAmount")
    @Mapping(target = "coins",
            source = "dto.coins")
    @Mapping(target = "userWalletAddress",
            source = "dto.userWalletAddress")
    @Mapping(target = "blockchainTxHash",
            source = "dto.blockchainTxHash")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    TransactionServiceOuterClass.TransactionRequest mapToGrpcReq(
            TransactionDTO dto
    );

    @Mapping(target = "serverWalletAddress", source = "response.serverWalletAddress")
    @Mapping(target = "priceInXMR",
            expression = "java(new BigInteger(response.getPriceInXmr()))")
    @Mapping(target = "coins", source = "response.coins")
    @Mapping(target = "status", source = "response.status")
    PaymentAddressDTO mapToGrpcResp(
            TransactionServiceOuterClass.PaymentAddressResponse response
    );

}

package pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.dto.TransactionDTO;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.entity.Transaction;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.user.service.UserAuthService;

@Mapper(componentModel="spring")
public interface TransactionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "userId",
            expression = "java(userAuthService.getUserId())")
    Transaction mapToEntity(TransactionDTO dto, @Context UserAuthService userAuthService);

    @Mapping(target = "transactionId", source = "id")
    TransactionDTO mapToDto(Transaction entity);
}

package pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.mapper;

import lombok.AllArgsConstructor;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.dto.TransactionDTO;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.entity.Transaction;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.user.service.UserAuthService;

@Component
@AllArgsConstructor
public abstract class TransactionMapper {

    private final UserAuthService userAuthService;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "userId",
            expression = "java(userAuthService.getUserId())")
    public abstract Transaction mapToEntity(TransactionDTO dto);

    @Mapping(target = "transactionId", source = "id")
    public abstract TransactionDTO mapToDto(Transaction entity);
}

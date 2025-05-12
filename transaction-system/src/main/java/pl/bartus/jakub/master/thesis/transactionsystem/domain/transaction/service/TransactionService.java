package pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.dto.TransactionDTO;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.entity.Transaction;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.mapper.TransactionMapper;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.repository.TransactionRepository;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.user.service.UserAuthService;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionMapper transactionMapper;
    private final UserAuthService userAuthService;
    private final TransactionRepository transactionRepository;

    public TransactionDTO create(TransactionDTO request){
        Transaction transaction = transactionMapper.mapToEntity(request, userAuthService);
        return transactionMapper.mapToDto(transactionRepository.save(transaction));
    }
}

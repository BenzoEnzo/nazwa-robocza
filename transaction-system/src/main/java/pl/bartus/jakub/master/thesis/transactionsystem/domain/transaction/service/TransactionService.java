package pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.dto.TransactionDTO;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.entity.Transaction;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.mapper.TransactionMapper;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.repository.TransactionRepository;
@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;

    public TransactionDTO create(TransactionDTO request){
        Transaction transaction = transactionMapper.mapToEntity(request);
        return transactionMapper.mapToDto(transactionRepository.save(transaction));
    }
}

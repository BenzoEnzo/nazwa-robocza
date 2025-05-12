package pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.dto.TransactionDTO;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.service.TransactionService;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.user.enumerated.UserGroup;
import pl.bartus.jakub.master.thesis.transactionsystem.security.SecuredByGroup;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    @SecuredByGroup(UserGroup.DEFAULT)
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.create(request));
    }
}

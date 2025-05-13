package pl.bartus.jakub.master.thesis.transactionsystem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartus.jakub.master.thesis.transactionsystem.api.dto.PaymentAddressDTO;
import pl.bartus.jakub.master.thesis.transactionsystem.api.dto.TransactionDTO;
import pl.bartus.jakub.master.thesis.transactionsystem.external.keycloak.user.enumerated.UserGroup;
import pl.bartus.jakub.master.thesis.transactionsystem.api.TransactionApi;
import pl.bartus.jakub.master.thesis.transactionsystem.external.keycloak.security.SecuredByGroup;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionApi transactionApi;

    @PostMapping
    @SecuredByGroup(UserGroup.DEFAULT)
    public ResponseEntity<PaymentAddressDTO> createTransaction(@Valid @RequestBody TransactionDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionApi.createTransaction(request));
    }
}

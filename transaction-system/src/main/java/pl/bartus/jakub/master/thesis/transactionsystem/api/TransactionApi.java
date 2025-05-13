package pl.bartus.jakub.master.thesis.transactionsystem.api;

import pl.bartus.jakub.master.thesis.transactionsystem.api.dto.PaymentAddressDTO;
import pl.bartus.jakub.master.thesis.transactionsystem.api.dto.TransactionDTO;

public interface TransactionApi {
       PaymentAddressDTO createTransaction(TransactionDTO request);
}

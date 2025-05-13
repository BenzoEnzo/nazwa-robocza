package pl.bartus.jakub.master.thesis.transactionsystem.api.dto;

import pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.enumerated.TransactionStatus;

import java.math.BigInteger;

public record PaymentAddressDTO(
        String serverWalletAddress,
        BigInteger priceInXMR,
        Integer coins,
        TransactionStatus status) {
}

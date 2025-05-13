package pl.bartus.jakub.master.thesis.transactionsystem.api.dto;

import java.math.BigInteger;

public record PaymentAddressDTO(
                                String serverWalletAddress,
                                BigInteger priceInXMR,
                                Integer coins) {
}

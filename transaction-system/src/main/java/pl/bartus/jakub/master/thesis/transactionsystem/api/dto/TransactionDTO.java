package pl.bartus.jakub.master.thesis.transactionsystem.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.enumerated.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Value
public class TransactionDTO {
    UUID userId;
    Long transactionId;
    LocalDateTime createdAt;
    LocalDateTime updatedAt = LocalDateTime.now();
    @NotNull
    TransactionStatus status;
    @NotNull
    BigDecimal xmrAmount;
    @NotNull
    Integer coins;
    @NotNull
    String userWalletAddress;
    String blockchainTxHash;
}

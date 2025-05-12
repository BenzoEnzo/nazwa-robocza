package pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.transaction.enumerated.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class Transaction {

    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "user_id", columnDefinition = "uuid")
    private UUID userId;

    @Column(nullable = false, updatable = false)
    private final LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status = TransactionStatus.PENDING;

    @Column(name = "xmr_amount",
            precision = 18, scale = 8, nullable = false)
    private BigDecimal xmrAmount;

    @Column(nullable = false)
    private Integer coins;

    @Column(name = "user_wallet_address", length = 95, nullable = false)
    private String userWalletAddress;

    @Column(name = "blockchain_tx_hash", length = 128, unique = true)
    private String blockchainTxHash;

    @Version
    private Long version;
}

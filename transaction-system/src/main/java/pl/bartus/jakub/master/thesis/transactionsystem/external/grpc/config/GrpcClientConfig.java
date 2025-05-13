package pl.bartus.jakub.master.thesis.transactionsystem.external.grpc.config;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.bartus.jakub.master.thesis.transactionsystem.TransactionServiceGrpc;

@Configuration
public class GrpcClientConfig {

    @Value("${grpc.server.host}")
    private String grpcHost;

    @Value("${grpc.server.port}")
    private int grpcPort;

    @Bean(destroyMethod = "shutdownNow")
    public ManagedChannel grpcChannel() {
        return ManagedChannelBuilder
                .forAddress(grpcHost, grpcPort)
                .usePlaintext()
                .build();
    }

    @Bean
    public TransactionServiceGrpc.TransactionServiceBlockingStub transactionBlockingStub(ManagedChannel grpcChannel) {
        return TransactionServiceGrpc.newBlockingStub(grpcChannel);
    }
}

package pl.bartus.jakub.master.thesis.transactionsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "pl.bartus.jakub.master.thesis.transactionsystem")
public class TransactionSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionSystemApplication .class, args);
    }

}


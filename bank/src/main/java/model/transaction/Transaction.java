package model.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;


@Getter
@AllArgsConstructor
@Builder
public class Transaction {
    private int transactionNumber;
    private int transactionAmount;
    private int transactionWBalance;
    private int transactionDBalance;
    private int transactionWAccountNumber;
    private int transactionDAccountNumber;
    private Timestamp transactionCreatedAt;
}

package model.transaction;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Getter
public class Transaction {
    private Integer transactionNumber; //PK
    private Integer transactionAmount;
    private Integer transactionWBalance;
    private Integer transactionDBalance;
    private Integer transactionWAccountNumber;
    private Integer transactionDAccountNumber;
    private Timestamp transactionCreatedAt;

    @Builder

    public Transaction(Integer transactionNumber, Integer transactionAmount, Integer transactionWBalance, Integer transactionDBalance, Integer transactionWAccountNumber, Integer transactionDAccountNumber, Timestamp transactionCreatedAt) {
        this.transactionNumber = transactionNumber;
        this.transactionAmount = transactionAmount;
        this.transactionWBalance = transactionWBalance;
        this.transactionDBalance = transactionDBalance;
        this.transactionWAccountNumber = transactionWAccountNumber;
        this.transactionDAccountNumber = transactionDAccountNumber;
        this.transactionCreatedAt = transactionCreatedAt;
    }
}

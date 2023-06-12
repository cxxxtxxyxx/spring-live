package service;

import model.account.Account;
import model.account.AccountDAO;
import model.transaction.Transaction;
import model.transaction.TransactionDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionService {
    private AccountDAO accountDAO;
    private TransactionDAO transactionDAO;
    private Connection connection;

    public TransactionService(AccountDAO accountDAO, TransactionDAO transactionDAO) {
        this.accountDAO = accountDAO;
        this.transactionDAO = transactionDAO;
        this.connection = transactionDAO.getConnection();
    }

    public Transaction transfer(String wAccountPassword, int wAccountNumber, int dAccountNumber, int amount) throws SQLException {
        Account wAccount = accountDAO.getAccountByNumber(wAccountNumber);
        Account dAccount = accountDAO.getAccountByNumber(dAccountNumber);

        // 계좌 존재 확인
        if (wAccount == null) {
            throw new RuntimeException("출금 계좌가 존재하지 않습니다");
        }
        if (dAccount == null) {
            throw new RuntimeException("입금 계좌가 존재하지 않습니다");
        }

        // 계좌 비밀번호 확인
        if (!wAccount.getAccountPassword().equals(wAccountPassword)) {
            throw new RuntimeException("출금 계좌의 비밀번호가 올바르지 않습니다");
        }

        // 계좌 잔액 확인
        if (wAccount.getAccountBalance() < amount) {
            throw new RuntimeException("출금 계좌의 잔액이 부족합니다");
        }

        // 계좌 업데이트
        int wBalance = wAccount.getAccountBalance() - amount;
        int dBalance = dAccount.getAccountBalance() + amount;
        accountDAO.updateAccount(wAccountNumber, wBalance);
        accountDAO.updateAccount(dAccountNumber, dBalance);

        // 트랜잭션 이력 남기기
        Transaction transaction = Transaction.builder()
                .transactionAmount(amount)
                .transactionWAccountNumber(wAccountNumber)
                .transactionDAccountNumber(dAccountNumber)
                .transactionWBalance(wBalance)
                .transactionDBalance(dBalance)
                .build();
        return transactionDAO.transfer(transaction);
    }
}

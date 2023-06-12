import db.DBConnection;
import model.account.Account;
import model.account.AccountDAO;
import model.transaction.Transaction;
import model.transaction.TransactionDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BankApp {
    public static void main(String[] args) {
        Connection connection = DBConnection.getInstance();

        AccountDAO accountDAO = new AccountDAO(connection);
//        accountDAO.createAccount(3333, "5678");
//        accountDAO.updateAccount(1000, 1111);
//        accountDAO.deleteAccount(1111);

//        Account account = accountDAO.getAccountNumber(2222);

//        System.out.println("account = " + account);

        List<Account> accountList = accountDAO.getAccountList();
        System.out.println("accountList = " + accountList);
        TransactionDAO transactionDAO = new TransactionDAO(connection);
        try {
// 이체 요청 정보
            int wAccountNumber = 2222;
            int dAccountNumber = 3333;
            int amount = 100;
// 트랜잭션 이력 남기기
            Transaction transaction = Transaction.builder()
                    .transactionAmount(amount)
                    .transactionWAccountNumber(wAccountNumber)
                    .transactionDAccountNumber(dAccountNumber)
                    .transactionWBalance(900)
                    .transactionDBalance(1100)
                    .build();
            transactionDAO.transfer(transaction);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

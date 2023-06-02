import db.DBConnection;
import model.Account;
import model.AccountDAO;

import java.sql.Connection;
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

    }
}

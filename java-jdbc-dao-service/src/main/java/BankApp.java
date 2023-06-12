import db.DBConnection;
import model.account.Account;
import model.account.AccountDAO;
import model.transaction.Transaction;
import model.transaction.TransactionDAO;
import service.TransactionService;

import java.sql.Connection;
import java.sql.SQLException;

public class BankApp {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBConnection.getInstance();
        AccountDAO accountDAO = new AccountDAO(connection);
        TransactionDAO transactionDAO = new TransactionDAO(connection);

//        Account account = accountDAO.createAccount(7777, "1234", 1000);
//        System.out.println(account);

        TransactionService transactionService = new TransactionService(accountDAO, transactionDAO);

        try {
            // 1. 클라이언트 요청
            String wAccountPassword = "5678";
            int amount = 400;
            int wAccountNumber = 1111;
            int dAccountNumber = 2222;

            // 2. 유효성 검사
            if (amount <= 0) {
                System.out.println("[유효성 오류] 0원 이하의 금액을 이체할 수 없습니다");
                return;
            }
            if (wAccountNumber == dAccountNumber) {
                System.out.println("[유효성 오류] 입출금 계좌가 동일할 수 없습니다");
                return;
            }

            // 3. 트랜잭션 시작
            // 아래 코드 주석 처리한 뒤 트랜잭션 쿼리에서 Insert에 t 지우고 테스트 해보기
            connection.setAutoCommit(false);

            // 4. 서비스 호출
            Transaction transaction = transactionService.transfer(wAccountPassword, wAccountNumber, dAccountNumber, amount);
            System.out.println(transaction);

            // 5. 트랜잭션 종료
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
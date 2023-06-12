package model.transaction;

import lombok.Getter;

import java.sql.*;

@Getter
public class TransactionDAO {
    private Connection connection;

    public TransactionDAO(Connection connection) {
        this.connection = connection;
    }

    public Transaction transfer(Transaction transaction) throws SQLException{
        String query = "INSERT INTO transaction_tb (transaction_amount, transaction_w_balance, transaction_d_balance, transaction_w_account_number, transaction_d_account_number, transaction_created_at) VALUES (?, ?, ?, ?, ?, now())";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, transaction.getTransactionAmount());
            statement.setInt(2, transaction.getTransactionWBalance());
            statement.setInt(3, transaction.getTransactionDBalance());
            statement.setInt(4, transaction.getTransactionWAccountNumber());
            statement.setInt(5, transaction.getTransactionDAccountNumber());
            int rowCount = statement.executeUpdate();
            if(rowCount > 0){
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedKey = generatedKeys.getInt(1); // 첫 번째 컬럼이 PK인 경우
                    return getTransactionByNumber(generatedKey);
                }
            }
        }
        return null;
    }

    public Transaction getTransactionByNumber(int transactionNumber) throws SQLException {
        String query = "SELECT * FROM transaction_tb WHERE transaction_number = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, transactionNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return buildTransactionFromResultSet(resultSet);
                }
            }
        }
        return null; // Account not found
    }

    private Transaction buildTransactionFromResultSet(ResultSet resultSet) throws SQLException {
        int transactionNumber = resultSet.getInt("transaction_number");
        int transactionAmount = resultSet.getInt("transaction_amount");
        int transactionWBalance = resultSet.getInt("transaction_w_balance");
        int transactionDBalance = resultSet.getInt("transaction_d_balance");
        int transactionWAccountNumber = resultSet.getInt("transaction_w_account_number");
        int transactionDAccountNumber = resultSet.getInt("transaction_d_account_number");
        Timestamp transactionCreatedAt = resultSet.getTimestamp("transaction_created_at");

        return Transaction.builder()
                .transactionNumber(transactionNumber)
                .transactionAmount(transactionAmount)
                .transactionWBalance(transactionWBalance)
                .transactionDBalance(transactionDBalance)
                .transactionWAccountNumber(transactionWAccountNumber)
                .transactionDAccountNumber(transactionDAccountNumber)
                .transactionCreatedAt(transactionCreatedAt)
                .build();
    }

}

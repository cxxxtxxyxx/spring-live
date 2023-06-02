package model;


import db.DBConnection;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Data (DB의 데이터) Access Object
@AllArgsConstructor
public class AccountDAO {
    private Connection connection;

    // account list
    public List<Account> getAccountList() {
        // 0. collection
        List<Account> accountList = new ArrayList<>();

        // 1. sql
        // 쿼리 바인딩을 위해 ? 를 사용하자 (항상 똑같은 쿼리를 사용하기 위해 + SQL Injection을 막을 수 있음)
        String query = "select * from account_tb";


        try {
            // 2. buffer
            // statement -> buffer
            PreparedStatement statement = connection.prepareStatement(query);

            // 3. send
            ResultSet resultSet = statement.executeQuery();



            // 4. cursor while
            while (resultSet.next()) {
                // 5. mapping (db result -> model) (parsing)
                // 프로젝션
                Account account = new Account(
                        resultSet.getInt("account_number"),
                        resultSet.getString("account_password"),
                        resultSet.getInt("account_balance"),
                        resultSet.getTimestamp("account_created_at")
                );

                // 6. collect
                accountList.add(account);
            }

            return accountList;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountList;
    }

    // account one
    public Account getAccountNumber(int accountNumber) {
        // 1. sql
        // 쿼리 바인딩을 위해 ? 를 사용하자 (항상 똑같은 쿼리를 사용하기 위해 + SQL Injection을 막을 수 있음)
        String query = "select * from account_tb where account_number = ?";


        try {
            // 2. buffer
            // statement -> buffer
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, accountNumber);

            // 3. send
            ResultSet resultSet = statement.executeQuery();


            // 4. mapping (db result -> model) (parsing)
            // 처음 커서는 속성 명을 나타내는 라인을 가리키고 있음
            // boolean isExist = resultSet.next();// 커서 한칸 내리기
            if (resultSet.next()) {
                Account account = new Account(
                        resultSet.getInt("account_number"),
                        resultSet.getString("account_password"),
                        resultSet.getInt("account_balance"),
                        resultSet.getTimestamp("account_created_at")
                );

                return account;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void createAccount(int accountNumber, String accountPassword) {
        // 1. sql
        // 쿼리 바인딩을 위해 ? 를 사용하자 (항상 똑같은 쿼리를 사용하기 위해 + SQL Injection을 막을 수 있음)
        String query = "insert into account_tb values (?, ?, 1000, now())";


        try {
            // 2. buffer
            // statement -> buffer
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(2, accountPassword);
            statement.setInt(1, accountNumber);

            // 3. send
            int result = statement.executeUpdate();
            System.out.println("result = " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateAccount(int accountBalance, int accountNumber) {
        // 1. sql
        // 쿼리 바인딩을 위해 ? 를 사용하자 (항상 똑같은 쿼리를 사용하기 위해 + SQL Injection을 막을 수 있음)
        String query = "update account_tb set account_balance = ? where account_number = ?";


        try {
            // 2. buffer
            // statement -> buffer
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, accountBalance);
            statement.setInt(2, accountNumber);

            // 3. send
            int result = statement.executeUpdate();
            System.out.println("result = " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteAccount(int accountNumber) {
        // 1. sql
        // 쿼리 바인딩을 위해 ? 를 사용하자 (항상 똑같은 쿼리를 사용하기 위해 + SQL Injection을 막을 수 있음)
        String query = "delete from account_tb where account_number = ?";


        try {
            // 2. buffer
            // statement -> buffer
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, accountNumber);

            // 3. send
            int result = statement.executeUpdate();
            System.out.println("result = " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

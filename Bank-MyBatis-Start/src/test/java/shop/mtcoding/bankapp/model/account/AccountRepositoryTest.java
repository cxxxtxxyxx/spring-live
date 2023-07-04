package shop.mtcoding.bankapp.model.account;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import shop.mtcoding.bankapp.model.user.UserRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@MybatisTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void findByUserId_test() {
        // Given
        int principalId = 1;

        // When
        List<Account> accountList = accountRepository.findByUserId(principalId);


        // Then
        assertThat(accountList.get(0).getNumber()).isEqualTo("1111");
    }
}

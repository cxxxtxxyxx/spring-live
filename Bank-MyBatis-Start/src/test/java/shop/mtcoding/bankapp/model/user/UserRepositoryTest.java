package shop.mtcoding.bankapp.model.user;


import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MybatisTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUsername_test() {
        // given
        String username = "ssar";

        // when
        User user = userRepository.findByUsername(username);

        // then
        System.out.println(user.getUsername());
        assertThat(user).isNotNull();

    }

    @Test
    void findById_test() {
        // given
        int id = 1;

        // when
        User user = userRepository.findById(id);

        // then
        System.out.println(user.getUsername());
        assertThat(user.getUsername()).isEqualTo("ssar");

    }

    @Test
    void findAll_test() {
        // Given

        // When
        List<User> userList = userRepository.findAll();

        // Then
        assertThat(userList.get(0).getUsername()).isEqualTo("ssar");
        assertThat(userList.get(1).getUsername()).isEqualTo("cos");

    }

    @Test
    public void insert_test() {
        // Given
        String username = "ssar";
        String password = "1234";
        String fullName = "쌀";

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFullname(fullName);

        // When


        // Then

        assertThrows(DuplicateKeyException.class, () -> {
            int result = userRepository.insert(user);
        });
    }
}

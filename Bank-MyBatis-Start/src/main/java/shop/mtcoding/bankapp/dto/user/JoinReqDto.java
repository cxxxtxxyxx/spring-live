package shop.mtcoding.bankapp.dto.user;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import shop.mtcoding.bankapp.model.user.User;

@Getter
@Setter
@ToString
public class JoinReqDto {
    private String username;
    private String password;
    private String fullname;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .fullname(fullname)
                .build();
    }
}

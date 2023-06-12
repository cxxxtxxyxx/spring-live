package shop.mtcoding.bankapp.dto.user;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class LoginReqDto {

    private String username;
    private String password;
}

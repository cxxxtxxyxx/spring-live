package shop.mtcoding.bankapp.model.user;

import java.sql.Timestamp;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String fullname;
    private Timestamp createdAt;
}

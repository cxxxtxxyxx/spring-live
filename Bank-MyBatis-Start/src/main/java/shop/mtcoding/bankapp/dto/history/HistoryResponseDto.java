package shop.mtcoding.bankapp.dto.history;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class HistoryResponseDto {
    private Integer id;
    private Long amount;
    private Long balance;
    private String sender;
    private String receiver;
    private Timestamp createdAt;
}

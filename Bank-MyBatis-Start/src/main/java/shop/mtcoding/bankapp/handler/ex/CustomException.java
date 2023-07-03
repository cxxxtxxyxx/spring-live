package shop.mtcoding.bankapp.model.history.ex;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }
}

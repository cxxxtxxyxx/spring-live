package shop.mtcoding.bankapp.handler;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.mtcoding.bankapp.handler.ex.AuthException;
import shop.mtcoding.bankapp.handler.ex.CustomException;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public String basicException(Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append("alert('" + e.getMessage() + "');");
        sb.append("history.back();");
        sb.append("</script>");
        return sb.toString();
    }

    @ExceptionHandler(AuthException.class)
    public String authException(Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append("alert('" + e.getMessage() + "');");
        sb.append("location.href='/loginForm';");
        sb.append("</script>");
        return sb.toString();
    }
}

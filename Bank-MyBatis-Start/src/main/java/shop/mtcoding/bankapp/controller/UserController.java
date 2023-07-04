package shop.mtcoding.bankapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.bankapp.dto.user.JoinReqDto;
import shop.mtcoding.bankapp.dto.user.LoginReqDto;
import shop.mtcoding.bankapp.handler.ex.CustomException;
import shop.mtcoding.bankapp.model.user.User;
import shop.mtcoding.bankapp.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final HttpSession session;

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }


    // 로그인은 select지만, post 요청 (Body로 데이터를 전송해야 하기 때문)
    @PostMapping("/login")
    public String login(LoginReqDto loginReqDto) {
        if (loginReqDto.getUsername() == null || loginReqDto.getUsername().isEmpty()) {
            throw new CustomException("username을 입력해주세요");
        }

        if (loginReqDto.getPassword() == null || loginReqDto.getPassword().isEmpty()) {
            throw new CustomException("password을 입력해주세요");
        }

        // 서비스 호출
        User principal = userService.로그인(loginReqDto);

        session.setAttribute("principal", principal);

        // 세션에 저장 (로그인 인증 처리)

        return "redirect:/";
    }
    

    // username=ssar&password=1234 -> x-www-form-urlencoded (스프링의 기본전략)
    // 1. 클라이언트의 요청 데이터 받기 (request 객체) (x-www-form-urlencoded) (application/json)
    // 2. 유효성 검사
    // 3. 서비스 호출
    // 4. 서비스로 정상적인 처리가 끝나면, 응답 (MessageConverter, ViewResolver)
    @PostMapping("/join")
    public String join(JoinReqDto joinReqDto) {
        if (joinReqDto.getUsername() == null || joinReqDto.getUsername().isEmpty()) {
            throw new CustomException("username을 입력해주세요");
        }

        if (joinReqDto.getPassword() == null || joinReqDto.getPassword().isEmpty()) {
            throw new CustomException("password를 입력해주세요");
        }

        if (joinReqDto.getFullname() == null || joinReqDto.getFullname().isEmpty()) {
            throw new CustomException("fullname을 입력해주세요");
        }

        userService.회원가입(joinReqDto);
        return "redirect:/loginForm";
    }
}

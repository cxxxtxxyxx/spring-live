package shop.mtcoding.bankapp.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.bankapp.dto.user.JoinReqDto;
import shop.mtcoding.bankapp.dto.user.LoginReqDto;
import shop.mtcoding.bankapp.model.history.ex.CustomException;
import shop.mtcoding.bankapp.model.user.User;
import shop.mtcoding.bankapp.model.user.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void 회원가입(JoinReqDto joinReqDto) {

        User user = userRepository.findByUsername(joinReqDto.getUsername());

        if (user != null) {
            throw new CustomException("username이 중복되었습니다.");
        }

        try {
            // delete, update, insert 무조건 서비스단에 try catch 걸어서 제어권을 뺏자
            int result = userRepository.insert(joinReqDto.toEntity());
            if (result == 0) {
                throw new CustomException("인서트 된 행이 없습니다.");
            }
        } catch (Exception e) {
            throw new CustomException("디비 에러 : " + e.getMessage());
        }

    }

    public User 로그인(LoginReqDto loginReqDto) {
        User user = userRepository.findByUsernameAndPassword(loginReqDto.getUsername(), loginReqDto.getPassword());

        if (user == null) {
            throw new CustomException("아이디, 비밀번호가 틀렸습니다");
        }

        return user;
    }
}

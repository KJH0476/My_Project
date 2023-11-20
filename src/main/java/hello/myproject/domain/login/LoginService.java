package hello.myproject.domain.login;

import hello.myproject.domain.user.User;
import hello.myproject.domain.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    @PostConstruct
    private void init(){
        User user = new User();
        user.setLoginId("aaa");
        user.setPassword("!aaa1234");
        user.setPasswordVerify("!aaa1234");
        user.setName("userA");
        user.setBirth("11월");
        user.setEmail("lkj0120@naver.com");

        User save = userRepository.save(user);
    }

    public User login(String loginId, String password){

        Optional<User> findUser = userRepository.findLoginId(loginId);
        if (findUser.isPresent()) {
            User user = findUser.get();
            log.info("user={}", user);
            if(user.getPassword().equals(password)) return user;
        }
        return null;
    }

}

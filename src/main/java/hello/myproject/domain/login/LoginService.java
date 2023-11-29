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
        user.setLoginId("aaa1234");
        user.setPassword("#aaa1234");
        user.setPasswordVerify("#aaa1234");
        user.setName("userA");
        user.setBirth("20000808");
        user.setPhoneNumber("01011111111");
        user.setEmail("lkj0120@naver.com");

        User user2 = new User();
        user2.setLoginId("kkk1234");
        user2.setPassword("#kkk1234");
        user2.setPasswordVerify("#kkk1234");
        user2.setName("userB");
        user2.setBirth("20000908");
        user2.setPhoneNumber("01022222222");
        user2.setEmail("lksdfs0@naver.com");

        userRepository.save(user);
        userRepository.save(user2);
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

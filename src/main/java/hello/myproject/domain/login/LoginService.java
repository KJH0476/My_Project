package hello.myproject.domain.login;

import hello.myproject.domain.user.User;
import hello.myproject.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public User login(String loginId, String password){

        Optional<User> findUser = userRepository.findLoginId(loginId);
        User user = findUser.get();
        if(user.getPassword().equals(password)) return user;
        else return null;
    }


}

package hello.myproject.web.user;

import hello.myproject.domain.user.User;
import hello.myproject.domain.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("add")
    public String addForm(@ModelAttribute User user){
        return "add/addForm";
    }

    @PostMapping("add")
    public String add(@Validated @ModelAttribute User user, BindingResult bindingResult){

        //아이디 중복 로직
        Optional<User> loginId = userRepository.findLoginId(user.getLoginId());
        if(loginId.isPresent()){
            log.info("아이디 중복");
            bindingResult.reject("AddFail", "사용하실 수 없는 아이디입니다.");
        }
        //비밀번호 검증 로직
        if(!(user.getPassword().equals(user.getPasswordVerify()))){
            log.info("비밀번호 검증 실패");
            bindingResult.reject("VerifyFail", "비밀번호가 일치하지 않습니다.");
        }
        if(bindingResult.hasErrors()) return "add/addForm";

        //성공 로직
        User saveUser = userRepository.save(user);
        log.info("Add Ok!! saveUser={}", saveUser);

        return "redirect:/";
    }
}

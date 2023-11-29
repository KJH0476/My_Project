package hello.myproject.web.user;

import hello.myproject.domain.user.User;
import hello.myproject.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/userInfo/{loginId}")
    public String userInfoPage(@PathVariable String loginId, Model model){
        Optional<User> user = userRepository.findLoginId(loginId);
        model.addAttribute("user", user.get());
        log.info("userInfo={}", user.get());

        return "page/userInfoPage";
    }

    @GetMapping("/edit")
    public String editPasswordForm(@ModelAttribute PasswordForm passwordForm){
        return "edit/editPasswordForm";
    }

    @PostMapping("/edit/{loginId}")
    public String editPassword(@Validated @ModelAttribute PasswordForm passwordForm,
                               BindingResult bindingResult, @PathVariable String loginId){
        log.info("editPassword");

        if(!(passwordForm.getPassword().equals(passwordForm.getPasswordVerify()))){
            log.info("비밀번호 검증 실패");
            bindingResult.reject("VerifyFail", "비밀번호가 일치하지 않습니다.");
        }
        if(bindingResult.hasErrors()){ return "edit/editPasswordForm"; }

        User user = new User();
        user.setPassword(passwordForm.getPassword());
        user.setPasswordVerify(passwordForm.getPasswordVerify());

        userRepository.update(loginId, user);
        return "redirect:/userInfo/"+loginId;
    }
}

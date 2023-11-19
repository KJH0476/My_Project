package hello.myproject.web;

import hello.myproject.domain.user.User;
import hello.myproject.domain.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepository;



    @GetMapping("/")
    public String mainPage(@SessionAttribute(name = "loginUser", required = false) User loginUser, Model model){

        if(loginUser == null){
            model.addAttribute("isLoggedIn", false);
            log.info("nologin");
            return "index";
        }
        //세션이 유지되면 로그인으로 이동
        model.addAttribute("loginUser", loginUser);
        model.addAttribute("isLoggedIn", true);
        log.info("login ok");
        return "index";
    }

}

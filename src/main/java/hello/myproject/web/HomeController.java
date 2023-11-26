package hello.myproject.web;

import hello.myproject.domain.user.User;
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

    @GetMapping("/")
    public String mainPage(@SessionAttribute(name = "loginUser", required = false) User loginUser, Model model){

        if(loginUser == null){
            log.info("noLogin");
            return "home/home";
        }
        //세션이 유지되면 로그인으로 이동
        model.addAttribute("loginUser", loginUser);
        log.info("Login ok");
        return "home/loginHome";
    }

}

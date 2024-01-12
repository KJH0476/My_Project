package hello.myproject.web;

import hello.myproject.domain.member.Member;
import hello.myproject.domain.member.google.GoogleMember;
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
    public String mainPage(@SessionAttribute(name = "loginMember", required = false) Member loginMember,
                           @SessionAttribute(name = "loginMember", required = false) GoogleMember googleMember,
                           Model model) {

        //구글 로그인 사용자와 일반 로그인 사용자를 구분
        if (googleMember != null) {
            // 구글 로그인 사용자 처리
            model.addAttribute("loginMember", googleMember);
            log.info("Google Login ok");
            return "home/loginHome";
        } else if (loginMember != null) {
            // 일반 로그인 사용자 처리
            model.addAttribute("loginMember", loginMember);
            log.info("Regular Login ok");
            return "home/loginHome";
        }
        log.info("no-login");
        return "home/home";
    }
}

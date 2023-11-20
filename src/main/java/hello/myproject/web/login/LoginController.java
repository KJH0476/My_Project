package hello.myproject.web.login;

import hello.myproject.domain.login.LoginService;
import hello.myproject.domain.user.User;
import hello.myproject.web.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm loginForm){
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm loginForm, BindingResult bindingResult,
                        HttpServletRequest request, @RequestParam(defaultValue = "/") String redirectUrl){
        if(bindingResult.hasErrors()) return "login/loginForm";

        User loginUser = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
        log.info("loginUser={}", loginUser);

        if (loginUser == null){
            bindingResult.reject("LoginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute(SessionConst.SESSION_NAME, loginUser);

        return "redirect:" + redirectUrl;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession httpSession = request.getSession(false);
        if(httpSession != null){
            httpSession.invalidate();
        }
        return "redirect:/";
    }
}

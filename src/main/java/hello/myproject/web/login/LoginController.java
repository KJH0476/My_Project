package hello.myproject.web.login;

import hello.myproject.domain.login.LoginService;
import hello.myproject.domain.member.Member;
import hello.myproject.domain.trace.callback.TraceTemplate;
import hello.myproject.domain.trace.logtrace.LogTrace;
import hello.myproject.web.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class LoginController {

    private final LoginService loginService;
    private final TraceTemplate template;

    public LoginController(LoginService loginService, LogTrace trace){
        this.loginService = loginService;
        this.template = new TraceTemplate(trace);
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm loginForm){
        return template.execute("LoginController.loginForm()", () -> {
            return "login/loginForm";
        });
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm loginForm, BindingResult bindingResult,
                        HttpServletRequest request, @RequestParam(defaultValue = "/") String redirectUrl, Model model){

        return template.execute("LoginController.login()", () -> {
            if(bindingResult.hasErrors()) return "login/loginForm";

            Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
            log.info("loginMember={}", loginMember);

            if (loginMember == null){
                bindingResult.reject("LoginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
                return "login/loginForm";
            }

            HttpSession httpSession = request.getSession(true);
            httpSession.setAttribute(SessionConst.SESSION_NAME, loginMember);

            return "redirect:" + redirectUrl;
        });
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

package hello.myproject.domain.login;

import hello.myproject.domain.member.Member;
import hello.myproject.domain.member.MemberService;
import hello.myproject.domain.trace.callback.TraceTemplate;
import hello.myproject.domain.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class LoginService {

    private final MemberService MemberService;
    private final TraceTemplate template;

    //생성자를 통한 의존성 주입
    public LoginService(MemberService memberService, LogTrace trace) {
        MemberService = memberService;
        this.template = new TraceTemplate(trace);
    }

    public Member login(String loginId, String password){

        return template.execute("LoginService.login()", () -> {
            Optional<Member> findMember = MemberService.findLoginId(loginId);
            if (findMember.isPresent()) {
                Member Member = findMember.get();
                if(Member.getPassword().equals(password)) return Member;
            }
            return null;
        });
    }

}

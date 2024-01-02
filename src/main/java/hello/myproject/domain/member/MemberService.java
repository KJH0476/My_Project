package hello.myproject.domain.member;

import hello.myproject.domain.member.Member;
import hello.myproject.domain.member.MemberRepository;
import hello.myproject.domain.trace.callback.TraceTemplate;
import hello.myproject.domain.trace.logtrace.LogTrace;
import hello.myproject.web.member.PasswordForm;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final TraceTemplate template;

    public MemberService(MemberRepository memberRepository, LogTrace trace){
        this.memberRepository = memberRepository;
        this.template = new TraceTemplate(trace);
    }

    public Member save(Member member){
        return memberRepository.save(member);
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Optional<Member> findLoginId(String loginId){
        return template.execute("MemberService.findLoginId()", () -> {
            Optional<Member> member = memberRepository.findLoginId(loginId);
            return member;
        });
    }

    public void update(String loginId, PasswordForm updatePassword){
        memberRepository.update(loginId, updatePassword);
    }
}

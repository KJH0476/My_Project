package hello.myproject.domain.login;

import hello.myproject.domain.member.Member;
import hello.myproject.domain.member.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberService MemberService;

    public Member login(String loginId, String password){

        Optional<Member> findMember = MemberService.findLoginId(loginId);
        if (findMember.isPresent()) {
            Member Member = findMember.get();
            log.info("Member={}", Member);
            if(Member.getPassword().equals(password)) return Member;
        }
        return null;
    }

}

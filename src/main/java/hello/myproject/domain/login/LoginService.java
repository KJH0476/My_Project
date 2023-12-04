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

    /*
    @PostConstruct
    private void init(){
        Member Member = new Member();
        Member.setLoginId("aaa1234");
        Member.setPassword("#aaa1234");
        Member.setPasswordVerify("#aaa1234");
        Member.setName("MemberA");
        Member.setBirth("20000808");
        Member.setPhoneNumber("01011111111");
        Member.setEmail("lkj0120@naver.com");

        Member Member2 = new Member();
        Member2.setLoginId("kkk1234");
        Member2.setPassword("#kkk1234");
        Member2.setPasswordVerify("#kkk1234");
        Member2.setName("MemberB");
        Member2.setBirth("20000908");
        Member2.setPhoneNumber("01022222222");
        Member2.setEmail("lksdfs0@naver.com");

        MemberRepository.save(Member);
        MemberRepository.save(Member2);
    }*/

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

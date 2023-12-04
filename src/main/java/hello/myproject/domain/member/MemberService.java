package hello.myproject.domain.member;

import hello.myproject.domain.member.Member;
import hello.myproject.domain.member.MemberRepository;
import hello.myproject.web.member.PasswordForm;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public Member save(Member member){
        return memberRepository.save(member);
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Optional<Member> findLoginId(String loginId){
        Optional<Member> member = memberRepository.findLoginId(loginId);
        return member;
    }

    public void update(String loginId, PasswordForm updatePassword){
        memberRepository.update(loginId, updatePassword);
    }
}

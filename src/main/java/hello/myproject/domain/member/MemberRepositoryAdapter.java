package hello.myproject.domain.member;

import hello.myproject.web.member.PasswordForm;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MemberRepositoryAdapter implements MemberRepository{

    /**
     * 기존 MemberRepository 유지하면서 SpringDataJpaMemberRepository 사용해주기 위해
     * Adapter 패턴을 사용하였다. MemberRepository 를 구현한 클래스를 생성하고 SpringDataJpaMemberRepository 를 주입받아
     * MemberRepository 의 메서드를 SpringDataJpaMemberRepository 의 메서드를 사용하여 구현하였다.
     * --------------------------------------------------------------
     * Adapter 패턴은 기존의 코드를 수정하지 않고 새로운 코드를 재사용하기 위해 사용한다.
     * 즉, 기존 코드를 클라이언트가 사용하는 인터페이스의 구현체로 바꿔주는 패턴이다.
     */
    private final SpringDataJpaMemberRepository springDataJpaMemberRepository;

    public MemberRepositoryAdapter(SpringDataJpaMemberRepository springDataJpaMemberRepository) {
        this.springDataJpaMemberRepository = springDataJpaMemberRepository;
    }

    @Override
    public Member save(Member Member) {
        return springDataJpaMemberRepository.save(Member);
    }

    @Override
    public Member findId(Long id) {
        return springDataJpaMemberRepository.findById(id).get();
    }

    @Override
    public List<Member> findAll() {
        return springDataJpaMemberRepository.findAll();
    }

    @Override
    public Optional<Member> findLoginId(String loginId) {
        return springDataJpaMemberRepository.findByLoginId(loginId);
    }

    @Override
    public void update(String loginId, PasswordForm updatePassword) {
        springDataJpaMemberRepository.update(loginId, updatePassword.getPassword(), updatePassword.getPasswordVerify());
    }

    @Override
    public void clearStore() {
        springDataJpaMemberRepository.deleteAll();
    }
}

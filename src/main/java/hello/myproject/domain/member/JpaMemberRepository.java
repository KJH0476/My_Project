package hello.myproject.domain.member;

import hello.myproject.domain.member.Member;
import hello.myproject.web.member.PasswordForm;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    @Override
    public Member save(Member Member) {
        em.persist(Member);
        log.info("JPA save Success!!");
        return Member;
    }

    @Override
    public Member findId(Long id) {
        Member Member = em.find(Member.class, id);
        return Member;
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m form Member m", Member.class).getResultList();
    }

    @Override
    public Optional<Member> findLoginId(String loginId) {
        List<Member> result = em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public void update(String loginId, PasswordForm updatePassword) {
        em.createQuery("update Member m set m.password = :newPassword, m.passwordVerify = :newPasswordVerify where m.loginId = :loginId")
                .setParameter("newPassword", updatePassword.getPassword())
                .setParameter("newPasswordVerify", updatePassword.getPasswordVerify())
                .setParameter("loginId", loginId)
                .executeUpdate();
    }

    @Override
    public void clearStore() {
        em.createQuery("delete from Member").executeUpdate();
    }
}

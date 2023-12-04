package hello.myproject.domain.member;

import hello.myproject.web.member.PasswordForm;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member Member);
    Member findId(Long id);
    List<Member> findAll();
    Optional<Member> findLoginId(String loginId);
    void update(String loginId, PasswordForm updatePassword);
    void clearStore();
}

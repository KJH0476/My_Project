package hello.myproject.domain.member;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> {

    /**
     * save, findById, findAll, deleteAll 메서드는 JpaRepository를 확장하므로 자동으로 상속받는다.
     */

    //로그인 아이디로 조회
    @Query("select m from Member m where m.loginId = :loginId")
    Optional<Member> findByLoginId(@Param("loginId") String loginId);

    @Transactional
    @Modifying
    @Query("update Member m set m.password = :newPassword, m.passwordVerify = :newPasswordVerify where m.loginId = :loginId")
    void update(@Param("loginId") String loginId, @Param("newPassword") String newPassword, @Param("newPasswordVerify") String newPasswordVerify);
}

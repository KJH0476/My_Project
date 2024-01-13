package hello.myproject.domain.member.google;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//구글 로그인 사용자 정보를 저장하는 JpaRepository
public interface GoogleMemberJpaRepository extends JpaRepository<GoogleMember, Long> {
    Optional<GoogleMember> findByEmail(String email);
}

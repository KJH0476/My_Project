package hello.myproject.domain.member.google;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 구글 로그인 멤버 엔티티
 * 만약 다른 소셜로그인 기능 추가할 거면 provider 필드 추가해서 사용 -> google, naver, kakao... 구분
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "GOOGLEMEMBER")
public class GoogleMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    //이메일과 동일
    @Column(nullable = false)
    private String loginId;

    @Builder
    public GoogleMember(String name, String email, String picture, Role role, String loginId) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.loginId = loginId;
    }

    public GoogleMember update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}

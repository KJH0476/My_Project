package hello.myproject.domain.auth;

import hello.myproject.domain.member.google.GoogleMember;
import hello.myproject.domain.member.google.GoogleMemberJpaRepository;
import hello.myproject.web.session.SessionConst;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2MemberService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final GoogleMemberJpaRepository googleMemberJpaRepository;
    private final HttpSession httpSession;

    /**
     * OAuth2UserRequest 타입의 객체를 매개변수로 받아, 사용자의 OAuth2 인증 정보를 처리
     * 이 객체에는 Google에서 받은 사용자 정보 및 OAuth2 토큰이 포함되어 있다.
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //기본 OAuth2UserService를 사용하여 사용자 정보를 로드
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        //Google OAuth2 인증 과정에서 사용자의 고유 식별자 필드의 이름을 가져옴
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        //OAuth2UserService를 통해 가져온 데이터를 담을 클래스
        OAuth2Attributes attributes = OAuth2Attributes.of(userNameAttributeName, oAuth2User.getAttributes());

        //로그인 한 멤버 정보
        GoogleMember member = saveOrUpdate(attributes);
        log.info("Google Login Member = {}", member);

        //세션 등록
        httpSession.setAttribute(SessionConst.SESSION_NAME, member);

        // 로그인한 멤버를 리턴함
        //loadUser 메서드의 최종 반환값은 Spring Security가 인증 과정에서 사용자를 식별하고 권한을 부여하는 데 사용
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(member.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    //Google 로그인 멤버 저장하고 이미 있는 데이터면 Update
    @Transactional
    public GoogleMember saveOrUpdate(OAuth2Attributes attributes) {
        GoogleMember member = googleMemberJpaRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());
        return googleMemberJpaRepository.save(member);

    }
}

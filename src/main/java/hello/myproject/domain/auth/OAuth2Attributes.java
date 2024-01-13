package hello.myproject.domain.auth;

import hello.myproject.domain.member.google.Role;
import hello.myproject.domain.member.google.GoogleMember;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuth2Attributes {
    private Map<String,Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;
    private String loginId;

    @Builder
    public OAuth2Attributes(Map<String,Object> attributes, String nameAttributeKey, String name, String email, String picture, String loginId){
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.loginId = loginId;
    }

    /**
     * 사용자 정보는 Map이기 때문에 변경해야함
     * 네이버, 카카오 등의 다른 소셜 로그인일 시 넘어오는 정보가 다를 수 있으므로
     * 메소드 만들고 이 of() 메소드를 활용해 무슨 소셜로그인이 넘어오는지 판단해 ofGoogle, ofNaver, ofKakao 등으로 분기처리
     */
    public static OAuth2Attributes of(String userNameAttributeName, Map<String,Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    public static OAuth2Attributes ofGoogle(String userNameAttributeName, Map<String,Object> attributes){
        // 미리 정의한 속성으로 빌드.
        return OAuth2Attributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .loginId((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public GoogleMember toEntity(){
        //gogoleMember 엔티티를 생성함
        return GoogleMember.builder().name(name).email(email).picture(picture).role(Role.USER).loginId(email).build();
    }
}

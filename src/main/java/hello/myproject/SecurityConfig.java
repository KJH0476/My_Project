package hello.myproject;

import hello.myproject.domain.member.google.Role;
import hello.myproject.domain.auth.CustomOAuth2MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2MemberService customOAuth2MemberService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())   //CSRF 보호 기능을 비활성화 -> CSRF는 웹 애플리케이션에서 사용자가 의도하지 않은 명령을 실행하는 것을 방지하는 보안 기능
                .headers(headers -> headers.    //X-Frame-Options를 비활성화 -> 클릭재킹 공격을 방지하는 보안 기능
                        frameOptions(frameOptions -> frameOptions.disable()))
                //현재 시큐리티가 아닌 mvc 활용한 일qks 로그인을 먼저 구현한 상태에서 OAuth2 로그인을 구현한 상태
                //-> OAuth2 로그인을 구현한 후에는 일반 로그인이 동작하지 않음.
                //따라서 WebSecurityCustomizer 따로 필터링 해주었음
                //시큐리티로 일반 로그인 설정할때 formLogin() 설정해주면 됨
                /*
                .authorizeHttpRequests(authorize -> {
                    //사용자 모두 /, /add, /login, /logout, /css/**, /image/**, /javascript/**, /error, /favicon.ico 에 접근 허용
                    //인증이 되어있는 사용자는 /api/** 에 접근 허용
                        authorize.requestMatchers("/", "/add", "/login", "/logout", "/css/**", "/*.ico", "/error", "/image/**", "/javascript/**").permitAll()
                        .requestMatchers("/api/**").hasRole(Role.USER.name())
                        .anyRequest().authenticated();  //로그인 사용자 모든 경로 접근 가능
                })
                 //일반 로그인 설정
                .formLogin(form -> form    //로그인 설정
                        .loginPage("/login")    //로그인 페이지 경로
                        .permitAll()    //로그인 페이지는 모든 사용자 접근 가능
                        .defaultSuccessUrl("/", false)  //로그인 성공 후 이동 경로
                )
                */
                .logout(logout -> logout    //로그아웃 설정
                        .logoutSuccessUrl("/")
                )
                .oauth2Login(oauth2 -> oauth2   //OAuth2 로그인 설정
                        .loginPage("/login")    //로그인 페이지 경로
                        //OAuth2 로그인 성공 후 customOAuth2MemberService 클래스를 통해 사용자 정보를 가져올 때의 설정들을 담당
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2MemberService)
                        )
                );
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        //특정 url 패턴에 대해 시큐리티 필터 적용을 제외
        return (web) -> web.ignoring()
                .requestMatchers("/", "/add", "/login", "/logout", "/css/**", "/*.ico", "/error", "/javascript/**");
    }
}

package holiday.fan.config;

import holiday.fan.repository.MemberRepository;
import holiday.fan.repository.jpa.MemberJpaRepository;
import holiday.fan.service.MemberServiceImpl;
import holiday.fan.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManager;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final EntityManager em;
    private final MemberJpaRepository memberJpaRepository;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository(), passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/member/**", "/market/**", "/fanboard/**", "/consult/**", "/order/**").permitAll()
                .antMatchers("/admin/**").access("hasRoleName('ADMIN') and hasAuthority('HOLIDAY') or hasAuthority('SLAVE')")
                .anyRequest().authenticated();

        http.csrf() //사용자의 의도치않은 위조요청을 하는 공격
            .disable() //근데 restApi는 서버에 인증정보 저장 안해서 안써도되어요

                .headers() //인증 헤더에는요
                .frameOptions() //아이프레임 옵션을 설정할게요(같은 페이지에서 다른 html 렌더링)
                .sameOrigin() //동일 도메인에서만 접근이 가능하게 할게요

                .and()
                .cors() //다른출처를 가진 리소스에 어떻게 대응할 것인가?

                .and()
                .logout() //스프링 시큐리티 디폴트 로그아웃
                .disable() //비활성화 할게요

                .sessionManagement() //시큐리티 세션 정책은요
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //스프링이 생성도 안 하고, 세션 있어도 사용 안할래요

                .and()
                .authorizeRequests() //요청 권한을 지정할건데요
                .antMatchers(HttpMethod.POST, "/login").permitAll() // POST요청으로 오는 /login 은 모두 접근 가능해요
                .anyRequest().authenticated() //다른 요청들은 인증해야만 가능해요

                .and()
//                .addFilterBefore() //스프링 시큐리티 인증(UsernamePasswordAuthenticationFilter) 전에, 이 필터를 먼저 써주세요
                .exceptionHandling() //예외 처리 기능 동작시킬거예요
//                .authenticationEntryPoint() //인증 실패하면 이렇게 해주시고
//                .accessDeniedHandler() //인가 실패하면 이렇게 처리해주세요
        ;

    }

    //fdfdeieirj로그인 석세시스핸들러
    //성공 , 실패 로직
    @Bean
    public MemberRepository memberRepository() {
        return new MemberRepository(em, memberJpaRepository);
    }
}

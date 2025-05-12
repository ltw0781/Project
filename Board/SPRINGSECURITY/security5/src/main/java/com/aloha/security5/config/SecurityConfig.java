package com.aloha.security5.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder; // 비밀번호 암호화 객체

    @Autowired
    private DataSource dataSource;          // application.properites 에 정의한 데이터 소스를 가져오는 객체

    

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 인가 설정
        http.authorizeRequests(requests -> requests
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/").permitAll()
                .anyRequest().authenticated());

        // 폼 로그인 설정
        // - 기본 로그인 경로 : /login
        http.formLogin(login -> login.permitAll());

        // 로그아웃 설정
        // - 기본 로그아웃 경로 : /logout
        http.logout(logout -> logout.logoutSuccessUrl("/")
                                    // .logoutUrl("/user/logout")
                                    .permitAll()
                    );
        // csrf 비활성화
        // http.csrf().disable();
    }




    /**
     * 🔐 사용자 인증 관리 메소드
     * ★ 인메모리 인증 방식
     * ★ JDBC 인증 방식
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // // AuthenticationManagerBuilder : 인증 관리 객체
        // auth.inMemoryAuthentication()
        //     // .withUser("아이디").password("비밀번호").roles("권한")
        //     // passwordEncoder.encode("비밀번호") : 비밀번호 암호화
        //     // BCryptPasswordEncoder 사용
        //     .withUser("user").password(passwordEncoder.encode("123456")).roles("USER")
        //     .and()
        //     .withUser("admin").password(passwordEncoder.encode("123456")).roles("ADMIN");
        // // NoOpPasswordEncoder 사용
        // // .withUser("user").password("123456").roles("USER")
        // // .and()
        // // .withUser("admin").password("123456").roles("ADMIN");
        // // ;

        // ⭐ 사용자 인증 쿼리
        String sql1 = " SELECT username as username, password as password, enabled "
                    + " FROM user "
                    + " WHERE username = ? ";

        // ⭐ 사용자 권한 쿼리
        String sql2 = " SELECT username as username, auth "
                    + " FROM user_auth "
                    + " WHERE username = ? ";

        auth.jdbcAuthentication()
            // 데이터 소스 등록
            .dataSource( dataSource )
            // 인증 쿼리    (아이디/비밀번호/활성여부)
            .usersByUsernameQuery(sql1)
            // 인가 쿼리    (아이디/권한)
            .authoritiesByUsernameQuery(sql2)
            // 비밀번호 암호화 방식 지정 - BCryptPasswordEncoder 또는 NoOpPasswordEncoder
            .passwordEncoder( passwordEncoder );

    }
}



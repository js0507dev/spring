package com.js0507dev.spring.settings;

import com.js0507dev.spring.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private AuthProvider authProvider;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/css/**", "/image/**", "/js/**", "/lib/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf();

    http.authorizeRequests()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/user/**").hasRole("MEMBER")
            .antMatchers("/login").permitAll()
            .antMatchers("/**").authenticated();

    http.formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/")
            .usernameParameter("email")
            .passwordParameter("password")
            .permitAll();

    http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true);

    http.exceptionHandling().accessDeniedPage("/error/403");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authProvider);
    auth.inMemoryAuthentication()
            .withUser("test@naver.com")
            .password(passwordEncoder().encode("test"))
            .roles("MEMBER");
  }
}

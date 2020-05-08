package com.js0507dev.spring.settings;

import com.js0507dev.spring.member.MemberService;
import com.js0507dev.spring.member.domain.MemberEntity;
import com.js0507dev.spring.member.domain.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class AuthProvider implements AuthenticationProvider {
  @Autowired
  private MemberService memberService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String userId = authentication.getName();
    String userPw = authentication.getCredentials().toString();

    log.info("===== login test "+userId);

    return authenticate(userId, userPw);
  }

  private Authentication authenticate(String id, String pw) throws AuthenticationException {
    Optional<MemberEntity> memberEntityWrapper = memberService.loadUserByUsername(id);
    MemberEntity memberEntity = memberEntityWrapper.orElse(null);
    if ( memberEntityWrapper.isEmpty() || !memberEntity.getPassword().equals(pw)) {
      log.error("로그인 정보가 올바르지 않습니다.");
      return null;
    }
    log.info("===== login"+memberEntity.getEmail());

    List<GrantedAuthority> authorities = new ArrayList<>();

    if (("admin@example.com").equals(memberEntity.getEmail())) {
      authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
    } else {
      authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
    }
    //return new MyAuthentication(id, pw, authorities, memberEntity);
    return new UsernamePasswordAuthenticationToken(memberEntity.getEmail(), memberEntity.getPassword(), authorities);
  }

  @Override
  public boolean supports(Class<?> auth) {
    return auth.equals(UsernamePasswordAuthenticationToken.class);
  }
}

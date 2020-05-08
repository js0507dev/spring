package com.js0507dev.spring.settings;

import com.js0507dev.spring.member.domain.MemberEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
@Setter
public class MyAuthentication extends UsernamePasswordAuthenticationToken {
  private static final long serialVersionUID = 1L;

  MemberEntity memberEntity;

  public MyAuthentication(String id, String pw, List<GrantedAuthority> authorities, MemberEntity memberEntity) {
    super(id, pw, authorities);
    this.memberEntity = memberEntity;
  }
}

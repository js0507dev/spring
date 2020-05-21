package com.js0507dev.spring.member;

import com.js0507dev.spring.member.domain.MemberDto;
import com.js0507dev.spring.member.domain.MemberEntity;
import com.js0507dev.spring.member.domain.MemberRole;
import com.js0507dev.spring.member.domain.SecurityMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MemberService implements UserDetailsService {
  @Autowired
  private MemberRepository memberRepository;

  @Validated
  public MemberEntity joinUser(MemberDto memberDto) {
    // 비밀번호 암호화
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

    return memberRepository.save(memberDto.toEntity());
  }

  public void autoLogin(MemberEntity member, HttpSession session) {
    List<MemberRole> roles = member.getRoles();
    roles.stream()
            .filter(memberRole -> (memberRole.getRoleName().equals("MEMBER") ||
                memberRole.getRoleName().equals("ADMIN")))
            .findAny()
            .ifPresent(memberRole -> {
              session.setAttribute("USER",member);
            });
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return Optional.ofNullable(memberRepository.findByEmail(email))
            .filter(m -> m != null)
            .map(m -> new SecurityMember(m.get())).get();
  }

  @Validated
  public MemberEntity save(MemberDto memberDto) {
    return memberRepository.save(memberDto.toEntity());
  }
}

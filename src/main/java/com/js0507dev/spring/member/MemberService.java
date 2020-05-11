package com.js0507dev.spring.member;

import com.js0507dev.spring.member.domain.MemberDto;
import com.js0507dev.spring.member.domain.SecurityMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberService implements UserDetailsService {
  @Autowired
  private MemberRepository memberRepository;

  @Transactional
  public Long joinUser(MemberDto memberDto) {
    // 비밀번호 암호화
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

    return memberRepository.save(memberDto.toEntity()).getId();
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return Optional.ofNullable(memberRepository.findByEmail(email))
            .filter(m -> m != null)
            .map(m -> new SecurityMember(m.get())).get();
  }
}

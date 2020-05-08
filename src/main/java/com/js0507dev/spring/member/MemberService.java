package com.js0507dev.spring.member;

import com.js0507dev.spring.member.domain.MemberDto;
import com.js0507dev.spring.member.domain.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberService {
  @Autowired
  private MemberRepository memberRepository;

  @Transactional
  public String joinUser(MemberDto memberDto) {
    // 비밀번호 암호화
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

    return memberRepository.save(memberDto.toEntity()).getEmail();
  }

  public Optional<MemberEntity> loadUserByUsername(String userEmail) throws UsernameNotFoundException {
    return memberRepository.findByEmail(userEmail);
  }
}

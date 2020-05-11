package com.js0507dev.spring.member;

import com.js0507dev.spring.member.domain.MemberDto;
import com.js0507dev.spring.member.domain.MemberEntity;
import com.js0507dev.spring.member.domain.MemberRole;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootTest
@Log
public class MemberRepositoryTest {
  @Autowired
  MemberRepository memberRepository;

  @Test
  public void saveTest() {
    for(int i=0; i<100; i++) {
      MemberDto memberDto = MemberDto.builder()
              .id(Integer.toUnsignedLong(i))
              .email("test"+i+"@test.com")
              .password("passwd"+i)
              .build();
      MemberRole memberRole = new MemberRole();
      if(i < 95) {
        memberRole.setRoleName("MEMBER");
      } else {
        memberRole.setRoleName("ADMIN");
      }
      memberDto.setRoles(Arrays.asList(memberRole));
      memberRepository.save(memberDto.toEntity());
    }
  }
}

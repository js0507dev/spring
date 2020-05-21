package com.js0507dev.spring.member;

import com.js0507dev.spring.member.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
  Optional<MemberEntity> findByEmail(String email);
  MemberEntity save(MemberEntity member);
}

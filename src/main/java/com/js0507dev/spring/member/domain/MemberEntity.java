package com.js0507dev.spring.member.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "member")
public class MemberEntity {
  @Id
  @Column(length = 60, nullable = false)
  private String email;

  @Column(length = 40, nullable = false)
  private String password;

  @Builder
  public MemberEntity(Long id, String email, String password) {
    this.email = email;
    this.password = password;
  }
}

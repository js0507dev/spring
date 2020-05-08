package com.js0507dev.spring.member.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class MemberDto {
  private String email;
  private String password;
  private LocalDateTime createDate;
  private LocalDateTime updateDate;

  @Builder
  public MemberDto(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public MemberEntity toEntity() {
    return MemberEntity.builder()
            .email(this.email)
            .password(this.password)
            .build();
  }
}

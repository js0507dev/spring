package com.js0507dev.spring.member.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class MemberDto {
  private Long id;
  private String email;
  private String password;
  private LocalDateTime createDate;
  private LocalDateTime updateDate;

  @Builder
  public MemberDto(Long id, String email, String password) {
    this.id = id;
    this.email = email;
    this.password = password;
  }

  public MemberEntity toEntity() {
    return MemberEntity.builder()
            .id(this.id)
            .email(this.email)
            .password(this.password)
            .build();
  }
}

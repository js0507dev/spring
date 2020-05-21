package com.js0507dev.spring.member.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
public class MemberDto {
  private Long id;
  @Email(message = "E-Mail 형식으로 입력해주세요.")
  @NotEmpty(message = "E-Mail은 필수입력 항목입니다.")
  private String email;
  @NotEmpty(message = "비밀번호는 필수입력 항목입니다.")
  @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[a-zA-Z0-9]).{8,}$", message = "비밀번호는 영어, 숫자를 포함하는 8자 이상의 문자열로 이루어져야 합니다.")
  private String password;
  private LocalDateTime createDate;
  private LocalDateTime updateDate;

  private List<MemberRole> roles;

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
            .roles(this.roles)
            .build();
  }
}

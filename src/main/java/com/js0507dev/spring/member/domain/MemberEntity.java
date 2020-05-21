package com.js0507dev.spring.member.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "member")
@EqualsAndHashCode(of = "email")
public class MemberEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 60, unique = true, nullable = false)
  private String email;

  @Column(length = 40, nullable = false)
  private String password;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "memberId")
  private List<MemberRole> roles;

  @Builder
  public MemberEntity(Long id, String email, String password) {
    this.id = id;
    this.email = email;
    this.password = password;
  }

  @Builder
  public MemberEntity(Long id, String email, String password, List<MemberRole> roles) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.roles = roles;
  }
}

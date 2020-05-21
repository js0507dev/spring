package com.js0507dev.spring.member;

import com.js0507dev.spring.member.domain.MemberDto;
import com.js0507dev.spring.member.domain.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/user/**")
public class MemberController {
  @Autowired
  private MemberService memberService;

  @GetMapping("join")
  public String join() {
    return "member/join";
  }

  @PostMapping("join")
  public String joinAction(HttpServletRequest request, @Valid MemberDto member) {
    MemberEntity joinMember = memberService.joinUser(member);
    memberService.autoLogin(joinMember, request.getSession());
    return "redirect:/";
  }
}

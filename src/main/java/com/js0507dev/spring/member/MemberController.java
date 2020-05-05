package com.js0507dev.spring.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/**")
public class MemberController {
  @GetMapping("login")
  public String login() {
    return "member/login";
  }
}

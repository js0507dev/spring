package com.js0507dev.spring.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
  @GetMapping("/")
  public String main() {
    return "common/main";
  }

  @GetMapping("/login")
  public String login() {
    return "common/login";
  }
}

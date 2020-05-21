package com.js0507dev.spring.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
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

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
  public String login(Model model, String error, String logout) {
    if(error != null) {
      log.info("test " + error);
      model.addAttribute("errorMsg", "로그인 정보가 올바르지 않습니다.");
    }
    if(logout != null) {
      log.info("test2 " + logout);
      model.addAttribute("message", "로그아웃 되었습니다.");
    }
    log.info("test3 ");

    return "common/login";
  }
}

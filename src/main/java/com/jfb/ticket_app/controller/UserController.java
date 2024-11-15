package com.jfb.ticket_app.controller;

import com.jfb.ticket_app.model.user.User;
import com.jfb.ticket_app.service.UserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/{id}")
  public User getUserById(@PathVariable("id") UUID id) {
    return userService.getUserById(id);
  }

}

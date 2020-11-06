package com.alura.forum.controllers;

import com.alura.forum.models.User;
import com.alura.forum.models.forms.LoginForm;
import com.alura.forum.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtService jwtService;

  @PostMapping
  public ResponseEntity<?> authenticate(@RequestBody @Valid LoginForm loginForm) throws AuthenticationException {
    User userAuthenticate = (User) this.authenticationManager.authenticate(this.convertToUsernamePasswordAuthenticationToken(loginForm)).getPrincipal();
    String token = this.jwtService.generateToken(userAuthenticate);
    token = "Bearer " + token;
    return ResponseEntity.ok().header("Authorization", token).build();
  }

  protected UsernamePasswordAuthenticationToken convertToUsernamePasswordAuthenticationToken(LoginForm loginForm) {
    return new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword());
  }

}

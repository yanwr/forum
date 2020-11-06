package com.alura.forum.services;

import com.alura.forum.models.User;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements UserDetailsService {

  @Autowired
  private UserService userService;

  @SneakyThrows
  @Override
  public UserDetails loadUserByUsername(String email) {
    try {
      return this.userService.showByEmail(email);
    } catch(UsernameNotFoundException ex) {
      throw new UsernameNotFoundException("User not found");
    }
  }

  public void authenticate(Long userId) throws Exception {
    try {
      User user = this.userService.showById(userId);
      UsernamePasswordAuthenticationToken userAuthenticate = new UsernamePasswordAuthenticationToken(user.getEmail(), null, user.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(userAuthenticate);
    } catch(Exception ex) {
      throw new Exception(ex);
    }
  }
}

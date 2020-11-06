package com.alura.forum.securities;

import com.alura.forum.services.AuthServiceImpl;
import com.alura.forum.services.JwtService;
import lombok.SneakyThrows;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter extends OncePerRequestFilter {

  private JwtService jwtService;
  private AuthServiceImpl authService;

  public AuthenticationFilter(JwtService jwtService, AuthServiceImpl authService) {
    this.jwtService = jwtService;
    this.authService = authService;
  }

  @SneakyThrows
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
    FilterChain next) {
    String currentToken = this.jwtService.getToken(request.getHeader("Authorization"));
    if (this.jwtService.isValid(currentToken)) {
      Long id = this.jwtService.getUserId(currentToken);
      this.authService.authenticate(id);
    }
    next.doFilter(request, response);
  }

}

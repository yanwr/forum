package com.alura.forum.config;

import com.alura.forum.securities.AuthenticationFilter;
import com.alura.forum.services.AuthServiceImpl;
import com.alura.forum.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
  prePostEnabled = true,
  securedEnabled = true,
  jsr250Enabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private AuthServiceImpl authService;

  @Autowired
  private JwtService jwtService;

  private static final String[] PUBLIC_ENDPOINTS = {"/h2-console/**"};
  private static final String[] PUBLIC_ENDPOINTS_GET = {"/topics", "/topics/*", "/actuator/**"};
  private static final String[] PUBLIC_ENDPOINTS_POST = {"/auth"};
  private static final String[] PUBLIC_FILES_TO_SWAGGER = {"/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**"};

  private static final String[] PRIVATE_ENDPOINTS_DELETE = {"/topics/*"};

  protected static final String AUTHORIZATION_MODERATOR = "MODERATOR";

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
      .antMatchers(PUBLIC_ENDPOINTS).permitAll()
      .antMatchers(HttpMethod.GET, PUBLIC_ENDPOINTS_GET).permitAll()
      .antMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS_POST).permitAll()
//      .antMatchers(HttpMethod.DELETE, PRIVATE_ENDPOINTS_DELETE).hasRole(AUTHORIZATION_MODERATOR)
      .anyRequest().authenticated()
      .and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and().addFilterBefore(new AuthenticationFilter(jwtService, authService), UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(authService).passwordEncoder(new BCryptPasswordEncoder());
  }

  @Override
  @Bean
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers(PUBLIC_FILES_TO_SWAGGER);
  }
}

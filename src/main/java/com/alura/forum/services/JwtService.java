package com.alura.forum.services;

import com.alura.forum.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

  @Value("${jwt.expiration}")
  private Long expiration;

  @Value("${jwt.secretKey}")
  private String secretKey;

  public String generateToken(User user) {
    return Jwts.builder()
      .setIssuer("API from alura forum")
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + this.expiration))
      .setSubject(user.getId().toString())
      .signWith(SignatureAlgorithm.HS256, this.secretKey)
      .compact();
  }

  public boolean isValid(String currentToken) {
    Claims claims = this.getTokenClaims(currentToken);
    if (claims != null) {
      String sub = claims.getSubject();
      Date expirationDate = claims.getExpiration();
      Date now = new Date();
      return sub != null && expirationDate != null && now.before(expirationDate);
    }
    return false;
  }

  protected Claims getTokenClaims(String currentToken) {
    try {
      return Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(currentToken).getBody();
    } catch(Exception ex) {
      return null;
    }
  }

  public String getToken(String currentToken) {
    if (currentToken == null || currentToken.isEmpty() || !currentToken.startsWith("Bearer ")) return null;
    return currentToken.substring(7);
  }

  public Long getUserId(String currentToken) {
    Claims claims = this.getTokenClaims(currentToken);
    if (claims != null) return Long.parseLong(claims.getSubject());
    return null;
  }
}

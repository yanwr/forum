package com.alura.forum.models.dtos;

import lombok.Data;

@Data
public class UserDto {
  private Long id;
  private String email;
  private String name;
}

package com.alura.forum.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {
  private Long id;
  private String message;
  private LocalDateTime createdAt;
  private UserDto user;
}

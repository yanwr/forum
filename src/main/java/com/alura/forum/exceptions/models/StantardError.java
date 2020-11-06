package com.alura.forum.exceptions.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StantardError {

  private static final long serialVersionUID = 1L;

  private LocalDateTime timestamp;
  private Integer status;
  private String error;
  private String message;
  private String path;
}

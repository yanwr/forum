package com.alura.forum.exceptions.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestDataInvalid {
  private final String field;
  private final String error;
}

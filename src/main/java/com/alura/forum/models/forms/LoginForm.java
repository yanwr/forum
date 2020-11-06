package com.alura.forum.models.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {

  @NotEmpty
  @NotNull
  private String email;

  @NotEmpty
  @NotNull
  private String password;
}

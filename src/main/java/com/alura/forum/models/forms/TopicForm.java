package com.alura.forum.models.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicForm {

  @NotNull
  @NotEmpty
  private String title;
  @NotNull
  @NotEmpty
  private String message;
  @NotNull
  @NotEmpty
  private String courseName;
}

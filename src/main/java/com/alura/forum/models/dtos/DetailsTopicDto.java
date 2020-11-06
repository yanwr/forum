package com.alura.forum.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.alura.forum.models.StatusTopic;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailsTopicDto {
  private TopicDto topic;
  private StatusTopic status;
  private List<AnswerDto> answers;
}

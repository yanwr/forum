package com.alura.forum.models.dtos;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TopicDto {
    private Long id;
    private String title;
    private String message;
    private LocalDateTime createdAt;
}

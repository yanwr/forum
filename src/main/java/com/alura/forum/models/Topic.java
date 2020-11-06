package com.alura.forum.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Topic {

  public Topic(String title, String message, Course course) {
    super();
    this.title = title;
    this.message = message;
    this.course = course;
  }

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String message;

	private LocalDateTime createdAt = LocalDateTime.now();

	@Enumerated(EnumType.STRING)
	private StatusTopic status = StatusTopic.NAO_RESPONDIDO;

	@ManyToOne
	private User user;

	@ManyToOne
	private Course course;

	@OneToMany(mappedBy = "topic")
	private List<Answer> answers = new ArrayList<>();

}

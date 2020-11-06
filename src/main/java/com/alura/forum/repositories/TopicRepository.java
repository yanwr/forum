package com.alura.forum.repositories;

import com.alura.forum.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
  // With Spring Data JPA
  List<Topic> findByCourseName(String name);
  // With Spring Data JPQL
  @Query("SELECT t FROM Topic t WHERE t.course.name = :name")
  List<Topic> showByCourseName(@Param("name") String name);
}

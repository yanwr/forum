package com.alura.forum.repositories;

import com.alura.forum.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
  Course findByName(String name);
}

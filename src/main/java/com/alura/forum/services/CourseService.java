package com.alura.forum.services;

import com.alura.forum.models.Course;
import java.util.List;

public interface CourseService {
  List<Course> index() throws Exception;
  Course create(Course user) throws Exception;
}

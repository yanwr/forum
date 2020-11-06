package com.alura.forum.services;

import com.alura.forum.models.Course;
import com.alura.forum.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

  @Autowired
  private CourseRepository courseRepository;

  @Override
  public List<Course> index() throws Exception {
    try {
      return this.courseRepository.findAll();
    } catch(Exception ex) {
      throw new Exception(ex.getMessage());
    }
  }

  @Override
  public Course create(Course course) throws Exception {
    try {
      return this.courseRepository.save(course);
    } catch(Exception ex) {
      throw new Exception(ex.getMessage());
    }
  }
}

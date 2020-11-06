package com.alura.forum.controllers;

import com.alura.forum.models.Course;
import com.alura.forum.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

  @Autowired
  private CourseService courseService;

  @GetMapping
  public List<Course> index() throws Exception{
    List<Course> courses = this.courseService.index();
    return courses;
  }

  @PostMapping
  public ResponseEntity<Course> create(@RequestBody Course course) throws Exception {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.courseService.create(course));
  }

}

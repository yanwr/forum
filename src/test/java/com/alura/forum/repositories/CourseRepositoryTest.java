package com.alura.forum.repositories;

import com.alura.forum.models.Course;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CourseRepositoryTest {

  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private CourseRepository courseRepository;

  @Test
  public void shouldReturnCourseWhenFindCourseByYourName() {
    String courseName = "HTML 5";
    Course html5 = new Course();
    html5.setName(courseName);
    html5.setCategory("TAGS");
    this.testEntityManager.persist(html5);

    Course course = this.courseRepository.findByName(courseName);
    Assert.assertNotNull(course);
    Assert.assertEquals(courseName, course.getName());
  }

  @Test
  public void givenCourseNotExistingShouldReturnNull() {
    String courseName = "JAVA";
    Course course = this.courseRepository.findByName(courseName);
    Assert.assertNull(course);
  }
}

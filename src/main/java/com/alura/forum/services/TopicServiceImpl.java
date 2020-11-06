package com.alura.forum.services;

import com.alura.forum.models.Course;
import com.alura.forum.models.Topic;
import com.alura.forum.models.forms.TopicForm;
import com.alura.forum.repositories.CourseRepository;
import com.alura.forum.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService {

  @Autowired
  private TopicRepository topicRepository;
  @Autowired
  private CourseRepository courseRepository;


  @Override
  public List<Topic> index() throws Exception {
    try {
      return this.topicRepository.findAll();
    } catch (Exception ex) {
      throw new Exception(ex.getMessage());
    }
  }

  @Override
  public Page<Topic> indexWithPaginationManual(Integer page, Integer quantity, String orderBy) throws Exception {
    try {
      Pageable pageable = PageRequest.of(page, quantity, Sort.Direction.ASC, orderBy);
      return this.topicRepository.findAll(pageable);
    } catch (Exception ex) {
      throw new Exception(ex.getMessage());
    }
  }

  @Override
  public Page<Topic> indexWithPaginationAuto(Pageable pageable) throws Exception {
    try {
      return this.topicRepository.findAll(pageable);
    } catch (Exception ex) {
      throw new Exception(ex.getMessage());
    }
  }

  @Override
  public Topic show(Long id) throws Exception {
    Optional<Topic> topic = this.topicRepository.findById(id);
    return topic.orElseThrow(Exception::new);
  }

  @Override
  public List<Topic> showByCourseName(String name) throws Exception {
    try {
      return this.topicRepository.findByCourseName(name);
    } catch (Exception ex) {
      throw new Exception(ex.getMessage());
    }
  }

  @Override
  public Topic create(TopicForm topicForm) throws Exception {
    try {
      Course course = this.courseRepository.findByName(topicForm.getCourseName());
      Topic topic = new Topic(topicForm.getTitle(), topicForm.getMessage(), course);
      return this.topicRepository.save(topic);
    } catch (Exception ex) {
      throw new Exception(ex.getMessage());
    }
  }

  @Override
  public Topic update(Long id, TopicForm topicForm) throws Exception {
    try {
      Topic currentTopic = this.show(id);
      currentTopic.getCourse().setName(topicForm.getCourseName());
      currentTopic.setTitle(topicForm.getTitle());
      currentTopic.setMessage(topicForm.getMessage());
      return currentTopic;
    } catch(Exception ex) {
      throw new Exception(ex.getMessage());
    }
  }

  @Override
  public void delete(Long id) throws Exception {
    try {
      this.topicRepository.deleteById(id);
    } catch(Exception ex) {
      throw new Exception(ex.getMessage());
    }
  }

}

package com.alura.forum.services;

import com.alura.forum.models.Topic;
import com.alura.forum.models.forms.TopicForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TopicService {
  List<Topic> index() throws Exception;
  Page<Topic> indexWithPaginationManual(Integer page, Integer quantity, String orderBy) throws Exception;
  Page<Topic> indexWithPaginationAuto(Pageable pageable) throws Exception;
  Topic show(Long id) throws Exception;
  List<Topic> showByCourseName(String name) throws Exception;
  Topic create(TopicForm topicForm) throws Exception;
  Topic update(Long id, TopicForm topicForm) throws Exception;
  void delete(Long id) throws Exception;
}

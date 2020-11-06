package com.alura.forum.controllers;

import com.alura.forum.models.Answer;
import com.alura.forum.models.dtos.AnswerDto;
import com.alura.forum.models.dtos.DetailsTopicDto;
import com.alura.forum.models.dtos.UserDto;
import com.alura.forum.models.forms.TopicForm;
import org.modelmapper.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.alura.forum.models.dtos.TopicDto;
import com.alura.forum.models.Topic;
import com.alura.forum.services.TopicService;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topics")
public class TopicController {

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private TopicService topicService;

  @GetMapping("/all")
  public List<TopicDto> index() throws Exception {
    List<Topic> topics = this.topicService.index();
    return topics.stream().map(this::convertToTopicDto).collect(Collectors.toList());
  }

  @GetMapping(params = {"page", "quantity", "orderBy"})
  public Page<TopicDto> indexWithPaginationManual(
    @RequestParam Integer page,
    @RequestParam Integer quantity,
    @RequestParam String orderBy
  ) throws Exception {
    Page<Topic> topics = this.topicService.indexWithPaginationManual(page, quantity, orderBy);
    return topics.map(this::convertToTopicDto);
  }

  @GetMapping
  public Page<TopicDto> indexWithPaginationAuto(
    @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable
  ) throws Exception {
    // when I use pageble auto, in URL PARAMS must have if I wanna
    // { page:int, size=int, sort=[columnName:string, sortDirection:string(desc, asc)] ex -> sort=createdAt,asc}
    Page<Topic> topics = this.topicService.indexWithPaginationAuto(pageable);
    return topics.map(this::convertToTopicDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DetailsTopicDto> show(@PathVariable Long id) throws Exception {
    Topic topic = this.topicService.show(id);
    return ResponseEntity.ok().body(this.convertToDetailsDto(topic));
  }

  @GetMapping(params = { "name" })
  public List<TopicDto> showByCourseName(@RequestParam String name) throws Exception {
    List<Topic> topics = this.topicService.showByCourseName(name);
    return topics.stream().map(this::convertToTopicDto).collect(Collectors.toList());
  }

  @PostMapping
  @Transactional
  public ResponseEntity<TopicDto> create(@RequestBody @Valid TopicForm topicForm) throws Exception {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.convertToTopicDto(this.topicService.create(topicForm)));
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<TopicDto> update(@PathVariable Long id, @RequestBody @Valid TopicForm topicForm) throws Exception {
    return ResponseEntity.ok().body(this.convertToTopicDto(this.topicService.update(id, topicForm)));
  }


  @PreAuthorize("hasRole('MODERATOR')")
  @DeleteMapping("/{id}")
  @Transactional
  public HttpStatus delete(@PathVariable Long id) throws Exception {
    this.topicService.delete(id);
    return HttpStatus.OK;
  }


  protected TopicDto convertToTopicDto(Topic topic) {
    return modelMapper.map(topic, TopicDto.class);
  }

  protected DetailsTopicDto convertToDetailsDto(Topic topic) {
   return modelMapper.typeMap(Topic.class, DetailsTopicDto.class)
    .addMappings(mapper -> {
      mapper.map(x -> this.convertToTopicDto(topic), DetailsTopicDto::setTopic);
      mapper.map(x -> topic.getStatus(), DetailsTopicDto::setStatus);
      mapper.map(x -> this.convertToListAnswerDto(topic.getAnswers()), DetailsTopicDto::setAnswers);
    }).map(topic);
  }

  protected List<AnswerDto> convertToListAnswerDto(List<Answer> answers) {
    return answers.stream().map( x -> {
      AnswerDto answerDto = new AnswerDto();
      answerDto.setId(x.getId());
      answerDto.setMessage(x.getMessage());
      answerDto.setCreatedAt(x.getCreatedAt());
      answerDto.setUser(modelMapper.map(x.getUser(), UserDto.class));
      return answerDto;
    }).collect(Collectors.toList());
  }

  protected Topic convertToEntity(TopicDto topicDTO) throws ParseException {
    return modelMapper.map(topicDTO, Topic.class);
  }
}

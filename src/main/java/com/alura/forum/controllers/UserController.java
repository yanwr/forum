package com.alura.forum.controllers;

import com.alura.forum.models.dtos.UserDto;
import com.alura.forum.models.User;
import com.alura.forum.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private UserService userService;

  @PreAuthorize("hasRole('MODERATOR')")
  @GetMapping
  public List<UserDto> index() throws Exception {
    List<User> users = this.userService.index();
    return users.stream().map(this::convertToDTO).collect(Collectors.toList());
  }

  @PostMapping
  public ResponseEntity<UserDto> create(@RequestBody User user) throws Exception {
    User _user = this.userService.create(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(this.convertToDTO(_user));
  }

  private UserDto convertToDTO(User user) {
    return modelMapper.map(user, UserDto.class);
  }

  private User convertToEntity(UserDto userDTO) throws ParseException {
    return modelMapper.map(userDTO, User.class);
  }
}

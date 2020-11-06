package com.alura.forum.services;

import com.alura.forum.models.User;
import java.util.List;

public interface UserService {
  List<User> index() throws Exception;
  User showByEmail(String email) throws Exception;
  User showById(Long id) throws Exception;
  User create(User user) throws Exception;
}

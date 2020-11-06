package com.alura.forum.services;

import com.alura.forum.models.User;
import com.alura.forum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public List<User> index() throws Exception {
    try {
      return this.userRepository.findAll();
    } catch(Exception ex) {
      throw new Exception(ex.getMessage());
    }
  }

  @Override
  public User showByEmail(String email) throws Exception {
    Optional<User> user = this.userRepository.findByEmail(email);
    return user.orElseThrow(Exception::new);
  }

  @Override
  public User showById(Long id) throws Exception {
    Optional<User> user = this.userRepository.findById(id);
    return user.orElseThrow(Exception::new);
  }

  @Override
  public User create(User user) throws Exception {
    try {
      return this.userRepository.save(user);
    } catch(Exception ex) {
      throw new Exception(ex.getMessage());
    }
  }
}


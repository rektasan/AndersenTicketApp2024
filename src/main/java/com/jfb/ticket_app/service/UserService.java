package com.jfb.ticket_app.service;

import com.jfb.ticket_app.model.user.User;

import com.jfb.ticket_app.repository.TicketRepository;
import com.jfb.ticket_app.repository.UserRepository;
import com.jfb.ticket_app.service.exceptions.UserNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public void saveUser(User user) {
    userRepository.save(user);
  }

  public User getUserById(UUID id) {
    Optional<User> user = userRepository.findById(id);
    return user.orElseThrow(() -> (new UserNotFoundException("User not found with id: " + id)));
  }

  public User getUserByName(String name) {
    Optional<User> user = userRepository.findUserByName(name);
    return user.orElseThrow(() -> (new UserNotFoundException("User not found with name: " + name)));
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User updateUser(UUID id, User updatedUser) {
    Optional<User> optionalUser = userRepository.findById(id);

    if (optionalUser.isPresent()) {
      User existingUser = optionalUser.get();
      existingUser.setName(updatedUser.getName());
      existingUser.setStatus(updatedUser.getStatus());
      return userRepository.save(existingUser);
    } else {
      throw new IllegalArgumentException("User with ID " + id + " not found.");
    }
  }

  public void deleteUserAndTickets(UUID id) {
    userRepository.deleteById(id);
  }

}

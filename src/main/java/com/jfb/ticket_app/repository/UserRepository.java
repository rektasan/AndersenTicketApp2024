package com.jfb.ticket_app.repository;

import com.jfb.ticket_app.model.user.User;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  Optional<User> findUserByName(String name);

}

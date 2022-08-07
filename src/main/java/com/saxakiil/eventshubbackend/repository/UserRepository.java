package com.saxakiil.eventshubbackend.repository;

import com.saxakiil.eventshubbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(final Long id);

    Optional<User> findByUsername(final String user);

    Optional<User> findByEmail(final String email);

    Boolean existsByUsername(final String username);

    Boolean existsByEmail(final String email);

    Boolean existsByAccountProfile_PhoneNumber(final String phoneNumber);
}

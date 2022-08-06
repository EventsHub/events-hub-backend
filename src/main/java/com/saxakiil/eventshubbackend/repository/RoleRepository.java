package com.saxakiil.eventshubbackend.repository;

import com.saxakiil.eventshubbackend.model.EnumRole;
import com.saxakiil.eventshubbackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(EnumRole name);
}

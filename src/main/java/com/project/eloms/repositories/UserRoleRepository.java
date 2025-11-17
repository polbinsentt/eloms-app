package com.project.eloms.repositories;

import com.project.eloms.entities.UserRole;
import com.project.eloms.types.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findByUserIdAndRoleType(long id, Roles role);
}

package com.project.eloms.repositories;

import com.project.eloms.entities.Role;
import com.project.eloms.types.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleName (String roleName);
}

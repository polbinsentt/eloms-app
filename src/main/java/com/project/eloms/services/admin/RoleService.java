package com.project.eloms.services.admin;

import com.project.eloms.dtos.ResponseDto;
import com.project.eloms.dtos.admin.RoleDto;
import com.project.eloms.entities.Role;
import com.project.eloms.repositories.RoleRepository;
import com.project.eloms.types.MessageType;
import com.project.eloms.utils.ResponseUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public ResponseDto setRole(RoleDto dto) {
        Role role = roleRepository.findByRoleName(dto.getRoleName()).orElse(null);

        if(role == null){
            role = new Role();
            role.setCreatedBy("ADMIN");
            role.setCreatedAt(new Date());
        }
        role.setRoleName(dto.getRoleName());
        role.setUpdatedAt(new Date());
        role.setUpdatedBy("ADMIN");

        roleRepository.save(role);

        return ResponseUtility.getSuccessResponse(MessageType.ROLE_SUCCESSFULLY_CREATED,role);
    }
}

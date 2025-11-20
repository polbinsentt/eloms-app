package com.project.eloms.controllers.admin;

import com.project.eloms.dtos.ResponseDto;
import com.project.eloms.dtos.admin.RoleDto;
import com.project.eloms.services.admin.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/role")
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/set")
    public ResponseDto setRole(@RequestBody RoleDto role){
        return roleService.setRole(role);
    }

    @PostMapping("/list")
    public ResponseDto listRole(){
        return roleService.listRole();
    }
}

package com.project.eloms.controllers.admin;

import com.project.eloms.dtos.DepartmentDto;
import com.project.eloms.dtos.ResponseDto;
import com.project.eloms.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/set")
    public ResponseDto setDepartment(@RequestBody DepartmentDto departmentDto) {
        return departmentService.setDepartment(departmentDto);
    }

    @PostMapping("/get/{id}")
    public ResponseDto getDepartmentById(@PathVariable Long id){
        return departmentService.getDepartmentById(id);
    }

    @PostMapping("/delete/{id}")
    public ResponseDto deleteDepartmentById(@PathVariable Long id){
        return departmentService.deleteDepartmentById(id);
    }
}

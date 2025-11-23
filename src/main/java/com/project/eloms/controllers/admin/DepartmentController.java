package com.project.eloms.controllers.admin;

import com.project.eloms.dtos.admin.DepartmentDto;
import com.project.eloms.dtos.ResponseDto;
import com.project.eloms.services.admin.DepartmentService;
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

    @PostMapping("/list")
    public ResponseDto listDepartment(){
        return departmentService.listDepartment();
    }

    @PostMapping("/delete/{departmentId}")
    public ResponseDto deleteDepartmentById(@PathVariable Long departmentId){
        return departmentService.deleteDepartmentById(departmentId);
    }
}

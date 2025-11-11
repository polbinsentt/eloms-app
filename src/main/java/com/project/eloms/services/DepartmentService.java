package com.project.eloms.services;

import com.project.eloms.dtos.DepartmentDto;
import com.project.eloms.dtos.ResponseDto;
import com.project.eloms.entities.Department;
import com.project.eloms.repositories.DepartmentRepository;
import com.project.eloms.types.MessageType;
import com.project.eloms.utils.ResponseUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public ResponseDto setDepartment(DepartmentDto departmentDto) {
        Department department = departmentRepository.findByDepartmentName(departmentDto.getDepartmentName()).orElse(null);
        if(department == null) {
            department = new Department();
            department.setDepartmentName(departmentDto.getDepartmentName());
            department.setCreatedAt(new Date());
            department.setCreatedBy("ADMIN");
        }
        department.setUpdatedAt(new Date());
        department.setUpdatedBy("ADMIN1");
        departmentRepository.save(department);

        return ResponseUtility.getSuccessResponse(MessageType.DEPARTMENT_SUCCESSFULLY_CREATED, department);
    }

    public ResponseDto getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElse(null);

        if(department == null)
            return ResponseUtility.getErrorResponse("E0001", MessageType.DEPARTMENT_NOT_FOUND);

        return ResponseUtility.getSuccessResponse(MessageType.DEPARTMENT_SUCCESSFULLY_FETCHED, department);
    }

    public ResponseDto deleteDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElse(null);

        if (department == null)
            return ResponseUtility.getErrorResponse("E0001", MessageType.DEPARTMENT_NOT_FOUND);

        departmentRepository.deleteById(id);
        return ResponseUtility.getSuccessResponse(MessageType.DEPARTMENT_SUCCESSFULLY_DELETED);
    }
}

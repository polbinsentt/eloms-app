package com.project.eloms.services.admin;

import com.project.eloms.dtos.admin.DepartmentDto;
import com.project.eloms.dtos.ResponseDto;
import com.project.eloms.entities.Department;
import com.project.eloms.repositories.DepartmentRepository;
import com.project.eloms.types.MessageType;
import com.project.eloms.utils.ResponseUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public ResponseDto setDepartment(DepartmentDto dto) {
        if (dto.getDepartmentName() == null || dto.getDepartmentName().isBlank())
            return ResponseUtility.getErrorResponse("E0001", MessageType.MISSING_REQUIRED_FIELD_INPUT);

        Department department = departmentRepository.findByDepartmentName(dto.getDepartmentName()).orElse(null);
        if (department != null)
            return ResponseUtility.getErrorResponse("E0002", MessageType.DEPARTMENT_ALREADY_EXIST);

        if(department == null) {
            department = new Department();
            department.setDepartmentName(dto.getDepartmentName());
            department.setCreatedAt(new Date());
            department.setCreatedBy("ADMIN");
        }

        department.setUpdatedAt(new Date());
        department.setUpdatedBy("ADMIN");
        departmentRepository.save(department);

        return ResponseUtility.getSuccessResponse(MessageType.DEPARTMENT_SUCCESSFULLY_CREATED);
    }


    public ResponseDto deleteDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElse(null);

        if (department == null)
            return ResponseUtility.getErrorResponse("E0001", MessageType.DEPARTMENT_NOT_FOUND);

        departmentRepository.deleteById(departmentId);

        return ResponseUtility.getSuccessResponse(MessageType.DEPARTMENT_SUCCESSFULLY_DELETED);
    }

    public ResponseDto listDepartment() {
        List<Map<String,Object>> res = departmentRepository.listAllDepartment();
        return ResponseUtility.getSuccessResponse(MessageType.SUCCESSFULLY_FETCHED_DATA,res);
    }
}

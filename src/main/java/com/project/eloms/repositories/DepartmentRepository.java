package com.project.eloms.repositories;

import com.project.eloms.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByDepartmentName(String departmentName);

    @Query(value = """
            SELECT
                JSON_ARRAYAGG(
                JSON_OBJECT(
                'id', id,
                'name', department_name
                ))   AS departments
            FROM department
            """, nativeQuery = true)
    List<Map<String, Object>> listAllDepartment();
}

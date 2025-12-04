package com.project.eloms.repositories;

import com.project.eloms.entities.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {


    Optional<Leave> findByLeaveType(String leaveType);


    @Query(value = """
            SELECT
                *
            FROM
                `leave`
            """, nativeQuery = true)
    List<Map<String,Object>> getLeaveList();
}

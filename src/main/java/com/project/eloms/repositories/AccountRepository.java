package com.project.eloms.repositories;

import com.project.eloms.entities.Account;
import com.project.eloms.types.SortOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(value = """
            SELECT
                 u.id                                                           AS id
                ,u.username                                                     AS username
                ,CONCAT(u.first_name," ", u.middle_name," ", u.last_name)       AS fullName
                ,u.email                                                        AS email
                ,u.birthdate                                                    AS birthdate
                ,u.phone_number                                                 AS phoneNumber
                ,d.department_name                                              AS departmentName
                ,u.is_active                                                    AS isActive
                ,u.role                                                         AS role
            FROM
                `user`                                                          AS u
            LEFT JOIN
                `department`                                                    AS d
                    ON d.id = u.department_id
            WHERE
                   (:username IS NULL OR u.username LIKE CONCAT('%', :username, '%')) AND
                   (:departmentName IS NULL OR d.department_name LIKE CONCAT('%', :departmentName, '%'))
            ORDER BY
                	CASE
                		WHEN :sortBy = 'username' AND :sortOrder = 'ASC' THEN u.username
                		WHEN :sortBy = 'birthdate' AND :sortOrder = 'ASC' THEN u.birthdate
                	END ASC,
                	CASE
                	    WHEN :sortBy = 'username' AND :sortOrder = 'DESC' THEN u.username
                	    WHEN :sortBy = 'birthdate' AND :sortOrder = 'DESC' THEN u.birthdate
                    END DESC
            """,nativeQuery = true)
    List<Map<String, Object>> listAccountWithFilter( String username,
                                                     String departmentName,
                                                     String sortOrder,
                                                     String sortBy
    );


    Optional<Account> findByUsername(String username);

    @Query(value = """
            SELECT
                 u.id                                                           AS id
                ,u.username                                                     AS username
                ,CONCAT(u.first_name," ", u.middle_name," ", u.last_name)       AS fullName
                ,u.email                                                        AS email
                ,u.birthdate                                                    AS birthdate
                ,u.phone_number                                                 AS phoneNumber
                ,d.department_name                                              AS departmentName
                ,u.is_active                                                    AS isActive
                ,u.role                                                         AS role
            FROM
                `user`                                                      AS u
            LEFT JOIN
                `department`                                                AS d
                    ON d.id = u.department_id
            WHERE
                u.id = :id
            """, nativeQuery = true)
    Optional<Map<String,Object>> findByAccountId(Long id);

    Optional<Account> findByPhoneNumber(String phoneNumber);
}

package com.project.eloms.repositories;

import com.project.eloms.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);

    @Query(value = """
            SELECT
                u.username                                                  AS username,
                CONCAT(u.first_name, ' ', u.middle_name, ' ', u.last_name)  AS fullName,
                u.birthdate                                                 AS birthDate,
                u.email                                                     AS email,
                MAX(p.phone_number)                                         AS phoneNumber,
                JSON_ARRAYAGG(ur.role_type)                                 AS roles,
                MAX(d.department_name)                                      AS departmentName
            FROM
                `user`                                                      AS u
            LEFT JOIN
                `department`                                                AS d
                    ON d.id = u.department_id
            LEFT JOIN
                `user_role`                                                 AS ur
                    ON ur.user_id = u.id
            LEFT JOIN
                `user_phone_number`                                         AS p
                    ON p.user_id = u.id
            WHERE
                u.id = :id
            GROUP BY u.id;
            """, nativeQuery = true)
    Optional<Map<String,Object>> findByAccountId(Long id);

    @Query(value = """
            SELECT
                u.*
            FROM
                `user`                                                      AS u
            LEFT JOIN
                `department`                                                AS d
                    ON d.id = u.department_id
            LEFT JOIN
                `user_role`                                                 AS ur
                    ON ur.user_id = u.id
            LEFT JOIN
                `user_phone_number`                                         AS p
                    ON p.user_id = u.id
            GROUP BY u.id;
            """,nativeQuery = true)
    List<Map<String, Object>> getAccounts();
}

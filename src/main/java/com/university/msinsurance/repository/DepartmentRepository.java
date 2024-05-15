package com.university.msinsurance.repository;

import com.university.msinsurance.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findById(Long id);

}

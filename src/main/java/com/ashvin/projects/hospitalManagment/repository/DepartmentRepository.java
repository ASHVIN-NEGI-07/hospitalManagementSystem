package com.ashvin.projects.hospitalManagment.repository;

import com.ashvin.projects.hospitalManagment.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
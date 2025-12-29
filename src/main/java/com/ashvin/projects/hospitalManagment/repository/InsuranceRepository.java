package com.ashvin.projects.hospitalManagment.repository;

import com.ashvin.projects.hospitalManagment.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}
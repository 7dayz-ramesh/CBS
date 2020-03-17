package com.imagination.cbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imagination.cbs.domain.EmployeeMapping;

@Repository("employeeMappingRepository")
public interface EmployeeMappingRepository extends JpaRepository<EmployeeMapping, Long>{

	public EmployeeMapping getEmployeeMappingByGoogleAccount(String googleAccount);
}

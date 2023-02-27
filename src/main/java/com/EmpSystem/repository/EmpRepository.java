package com.EmpSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EmpSystem.entity.Employee;
@Repository
public interface EmpRepository extends JpaRepository<Employee,Integer>{

}

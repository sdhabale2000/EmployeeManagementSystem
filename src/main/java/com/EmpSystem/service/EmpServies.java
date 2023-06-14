package com.EmpSystem.service;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EmpSystem.entity.Employee;
import com.EmpSystem.helper.Helper;
import com.EmpSystem.repository.EmpRepository;

@Service
public class EmpServies {

	@Autowired
	private EmpRepository repository;

	public void addEmp(Employee e) {

		repository.save(e);
	}

	public List<Employee> getAllEmp() {
		
		Iterable<Employee> empList = repository.findAll();
		ArrayList<Employee> list=new ArrayList<>();
		empList.forEach(x-> list.add(x));
		return list;
		 
		
		}
	
	public Employee getEmpById(Integer id) {
		
		Optional<Employee> e = repository.findById(id);
		
		if(e.isPresent()) {
		
			return e.get();
		}else {
			return null;
		}
		
		
		
	}
	public void  deleteEmpById(Integer id) {
		
		repository.deleteById(id);
	}
	
	public ByteArrayInputStream getActualData() {

		List<Employee> list = repository.findAll();
		
		
		ByteArrayInputStream excell = Helper.dataToExcel(list);
		
		
		
		return excell;
	}


//	@GetMapping("/empByName")
//	public List<Employee> findByName(@RequestParam String name) {
//
//		return employeeService.getEmpByName(name);
//	}

}

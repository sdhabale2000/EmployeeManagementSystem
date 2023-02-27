package com.EmpSystem.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.EmpSystem.entity.Employee;
import com.EmpSystem.service.EmpServies;

@Controller

public class EmpManagementController {
	
	@Autowired
	private EmpServies servies;
	
	@GetMapping( "/")
	public String home(Model m) {
		List<Employee> emp=servies.getAllEmp();
		
		m.addAttribute("emp",emp);
		
		return "index";

	}

	@GetMapping("/addemp")
	public String addEmpForm() {
		
		return "add_emp";
	}
	
	@PostMapping("/register")
	public String empRegister(@ModelAttribute Employee e,HttpSession session) {
		
		
		session.setAttribute("msg", "Employee Added successfully");
		
		servies.addEmp(e);
		
		return "redirect:/";
	}
	
	
	@GetMapping("/getall")
	public List<Employee> getAll() {

		return servies.getAllEmp();
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id,Model m) {
		
		Employee emp = servies.getEmpById(id);
		m.addAttribute("emp", emp);
		return "edit";
	}
	
	
	@PostMapping("/update")

	public String updateEmpById(@ModelAttribute Employee e, HttpSession session) {
		
		servies.addEmp(e);
		session.setAttribute("msg","Employee updated successfully.");
		return "redirect:/";
	}
	@GetMapping("/delete/{id}")
	public String deleteEmp(@PathVariable Integer id,HttpSession session) {
		
		servies.deleteEmpById(id);
		session.setAttribute("msg", "Employe deleted successfully ..");
		return "redirect:/";
	}

}

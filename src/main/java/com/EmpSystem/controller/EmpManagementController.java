package com.EmpSystem.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.EmpSystem.entity.Employee;
import com.EmpSystem.service.EmpServies;

@Controller

public class EmpManagementController {

	@Autowired
	private EmpServies servies;

	@GetMapping("/")
	public String home(Model m) {
		// List<Employee> emp=servies.getAllEmp();

		// m.addAttribute("emp",emp);

		return "index";

	}

	@GetMapping("/view")
	public String view(Model m) {
		List<Employee> emp = servies.getAllEmp();

		m.addAttribute("emp", emp);

		return "view";

	}

	@GetMapping("/addemp")
	public String addEmpForm() {

		return "add_emp";
	}

	@PostMapping("/register")
	public String empRegister(@ModelAttribute Employee e, HttpSession session) {

		session.setAttribute("msg", "Employee Added successfully");

		servies.addEmp(e);

		return "redirect:/view";
	}

	@GetMapping("/getall")
	public List<Employee> getAll() {

		return servies.getAllEmp();

	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model m) {

		Employee emp = servies.getEmpById(id);
		m.addAttribute("emp", emp);
		return "edit";
	}

	@PostMapping("/update")

	public String updateEmpById(@ModelAttribute Employee e, HttpSession session) {

		servies.addEmp(e);
		session.setAttribute("msg", "Employee updated successfully.");
		return "redirect:view";
	}

	@GetMapping("/delete/{id}")
	public String deleteEmp(@PathVariable Integer id, HttpSession session) {

		servies.deleteEmpById(id);
		session.setAttribute("msg", "Employe deleted successfully ..");
		return "redirect:/view";
	}

	@RequestMapping("/getExcell")
	public ResponseEntity<Resource> downloadExcell() {

		String fileName = "employee.xlsx";

		ByteArrayInputStream data = servies.getActualData();

		InputStreamResource file = new InputStreamResource(data);

		ResponseEntity<Resource> body = ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "Attachment; fileName=" + fileName)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);

//		ResponseEntity<Resource> body = ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "Attachment; fileName="+fileName)
//		.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
//		

		return body;
	}

}

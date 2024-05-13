package com.example;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpController {

	@Value("${server.port}")
	private int serverPort;

	@Autowired 
	private HttpServletRequest request;
	
	private static final Logger log = LoggerFactory.getLogger(EmpController.class);
	
	@Autowired
	EmpRepository empRepo;

	@GetMapping(value = {"/", "/welcome"})
	public String welcome() {
		return "Welcome.... IP : "+ serverPort + "  address: "+ request.getRemoteAddr()+ "  address: " + request.getRemoteHost() + "  address: "+ request.getLocalAddr() ;
	}
	
	@GetMapping("/employee")
	public List<Employee> getAllEmp() {
		log.info("getting list of employees");
		return empRepo.findAll();
	}

	@PostMapping(value = "/employee", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addEmp(@RequestBody Employee emp) {
		log.info("adding employees");
		return "successfully added emp with id: " + empRepo.save(emp);
	}
}

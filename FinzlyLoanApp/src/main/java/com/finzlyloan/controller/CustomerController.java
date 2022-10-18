package com.finzlyloan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.finzlyloan.dao.UserSessionDao;
import com.finzlyloan.entity.Customer;
import com.finzlyloan.entity.UserSession;
import com.finzlyloan.entity.dto.Login;
import com.finzlyloan.service.CustomerService;


@RestController
@CrossOrigin( origins = "http://localhost:4200")
public class CustomerController {
	
	@Autowired
	private CustomerService custSer;
	
	@Autowired
	private UserSessionDao session;
	
	@PostMapping("/customers/create")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer)throws Exception{
		System.out.println(customer);
		return new ResponseEntity<>(custSer.create(customer),HttpStatus.CREATED);
	}
	@PostMapping("/login")
	public ResponseEntity<UserSession> login(@RequestBody Login login) throws Exception{
		
		return  new ResponseEntity<>(custSer.login(login),HttpStatus.OK);
	}
	@GetMapping("/ping")
	public String ping() {
		return "hello";
	}
	
	public UserSession tokenValidation(String token) throws Exception {
			UserSession user=session.findByToken(token);
			if(user!=null) {
				return user;
			}else
			throw new Exception("Invalid Token");
		
		
	}

}

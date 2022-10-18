package com.finzlyloan.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finzlyloan.dao.CustomerDao;
import com.finzlyloan.dao.UserSessionDao;
import com.finzlyloan.entity.Customer;
import com.finzlyloan.entity.UserSession;
import com.finzlyloan.entity.dto.Login;

import net.bytebuddy.utility.RandomString;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao custDao;
	@Autowired
	private UserSessionDao session;
	
	public Customer create(Customer cus)throws Exception {
		try {
		System.out.println(cus);
			return custDao.save(cus);
		}catch(Exception exc){
			throw new Exception("Database Error");
		}
	}
	public UserSession login(Login log) throws Exception {
		Optional<Customer>	customer=custDao.findByEmailAndPassword(log.getEmail(),log.getPassword());
		 if(customer.get()==null) {
			 throw new Exception("User Not Found or Password Does not match");
		 }
		 System.out.println(customer.get());
		 UserSession user=new UserSession(RandomString.make(10),customer.get().getEmail(),LocalDateTime.now());
		 return session.save(user);
	}

}

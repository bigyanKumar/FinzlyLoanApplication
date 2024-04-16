package com.finzlyloan.controller;




import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finzlyloan.dao.CustomerDao;
import com.finzlyloan.dao.LoanDao;
import com.finzlyloan.dao.PaymentSchedulerDao;
import com.finzlyloan.entity.Customer;
import com.finzlyloan.entity.Loan;
import com.finzlyloan.entity.UserSession;
import com.finzlyloan.service.LoanService;

@RestController
@CrossOrigin( origins = "http://localhost:4200")
public class LoanController {
	
	@Autowired
	private LoanService loanSer;
	@Autowired
	private CustomerController custCont;
	@Autowired
	private CustomerDao custDao;
	@Autowired
	private LoanDao loanD;
	@Autowired
	private PaymentSchedulerDao paymentSchDao;
	
	
	@PostMapping("/createloan")
	public ResponseEntity<?> createLoan(@RequestBody Loan loan,@RequestParam("token") String token) throws Exception{
		UserSession user=custCont.tokenValidation(token);
		Optional<Customer> customer=custDao.findById(user.getEmail());
		loan.setCustomer(customer.get());
		System.out.println(loan);
		return new ResponseEntity<>(loanSer.createLoan(loan),HttpStatus.CREATED);
	}
	@GetMapping("findloans")
	public ResponseEntity<?> getAllLoan(@RequestParam("token")String token) throws Exception{
		UserSession user=custCont.tokenValidation(token);
		Optional<Customer> customer=custDao.findById(user.getEmail());
		return new ResponseEntity<>(loanD.findByCustomer(customer.get()),HttpStatus.OK);
	}
	@GetMapping("findgenratedloan/{loanId}")
	public ResponseEntity<?> findAllGenratedLoanById(@RequestParam("token")String token,@PathVariable("loanId") String loanId) throws Exception{
		UserSession user=custCont.tokenValidation(token);
		return new ResponseEntity<>(paymentSchDao.findByLoan(loanId),HttpStatus.OK);
	}
	@PutMapping("/payment/{schId}")
	public ResponseEntity<?> makePayment(@RequestParam("token")String token,@PathVariable("schId") String schId) throws Exception{
		UserSession user=custCont.tokenValidation(token);
		System.out.println("hellow");
		return new ResponseEntity<>(loanSer.payment(schId),HttpStatus.ACCEPTED);
	}
	

}

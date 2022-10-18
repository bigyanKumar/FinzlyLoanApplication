package com.finzlyloan.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finzlyloan.dao.LoanDao;
import com.finzlyloan.dao.PaymentSchedulerDao;
import com.finzlyloan.entity.Loan;
import com.finzlyloan.entity.PaymentScheduler;
import com.finzlyloan.globalExceptionHandler.LoanFailureException;

import net.bytebuddy.utility.RandomString;

@Service
public class LoanService {
	
	@Autowired
	private LoanDao loanDao;
	
	@Autowired
	private PaymentSchedulerDao paymentSchDao;
	
	public List<PaymentScheduler> createLoan(Loan loan) {
		PaymentScheduler paymentSch=new PaymentScheduler();
		List<PaymentScheduler> schdList=new ArrayList<>();
		Double intrest=null;
		Double amount=null;
		Integer tenuar=null;
		LocalDate date=LocalDate.now();
		loan.setDate(LocalDateTime.now());
		date=date.plusMonths(1);
		date=date.minusDays(date.getDayOfMonth()-1);
		loan.setLoanStartDate(date);
		if(loan.getPaymentFrequency().equalsIgnoreCase("YEARLY")) {
			tenuar=12;
		}else if(loan.getPaymentFrequency().equalsIgnoreCase("HALFYEARLY")) {
			tenuar=6;
		}else if(loan.getPaymentFrequency().equalsIgnoreCase("QUARTERLY")) {
			tenuar=4;
		}else {
			tenuar=1;
		}
		date=date.plusMonths(tenuar);
		date=date.minusDays(date.getDayOfMonth()-1);
		loan.setMaturityDate(date);
		loan.setId(RandomString.make(14));
		intrest=loan.getIntrestRate();
		amount=loan.getLoanAmount();
		double totalIntr=amount*intrest/100;
		Double totalAmount=amount+(amount*intrest/100);
		loan.setLoanAmount(totalAmount);
		loanDao.save(loan);
		System.out.println(loan);
		//
		double intrestUnit=0;
		if(loan.getPaymentType().equalsIgnoreCase("EVEN_PRINCIPAL")) {
			paymentSch.setAmount(totalAmount/tenuar);
			intrestUnit=totalIntr/tenuar;
		}else {
			paymentSch.setAmount(totalIntr/(tenuar-1));
			intrestUnit=totalIntr/(tenuar-1);
		}
		paymentSch.setPrincipal(totalAmount);
		paymentSch.setProjectedIntrest(amount*intrest/100);
		if(loan.getPaymentType().equalsIgnoreCase("EVEN_PRINCIPAL")) {
			for(int i=0; i<tenuar; i++) {
				if(i!=0) {
				paymentSch.setStatus("PROJECTED");
				}
				if(i==0) {
					paymentSch.setStatus("AWAITINGPAYMENT");
				}
				LocalDate Emidate=LocalDate.now();
				Emidate=Emidate.plusMonths(i+1);
				Emidate=Emidate.minusDays(Emidate.getDayOfMonth()-1);
				paymentSch.setPaymentDate(Emidate);
				paymentSch.setId(RandomString.make(12));
				paymentSch.setLoan(loan.getId());
				//
				if(i!=0) {
					paymentSch.setPrincipal(totalAmount-(totalAmount/tenuar*i));
					paymentSch.setProjectedIntrest(totalIntr-(intrestUnit*i));
				}
				//
				schdList.add(paymentSchDao.save(paymentSch));
				System.out.println(paymentSch);
			}
			
		}else {
			
			for(int i=0; i<tenuar; i++) {
				if(i==0) {
					paymentSch.setStatus("AWAITINGPAYMENT");
				}
				if(i!=0) {
					paymentSch.setStatus("PROJECTED");
				}
				LocalDate Emidate=LocalDate.now();
				Emidate=Emidate.plusMonths(i+1);
				Emidate=Emidate.minusDays(Emidate.getDayOfMonth()-1);
				paymentSch.setPaymentDate(Emidate);
				paymentSch.setId(RandomString.make(12));
				paymentSch.setLoan(loan.getId());
				//
				if(i!=0) {
					paymentSch.setPrincipal(totalAmount-(intrestUnit*i));
					paymentSch.setProjectedIntrest(totalIntr-(intrestUnit*i));
				}
				if(i==tenuar-1) {
					paymentSch.setAmount(amount);
				}
				
				//
				schdList.add(paymentSchDao.save(paymentSch));
				
			}
		
		}
		return schdList;
	}
	public Object payment(String schdulerId){
		Optional<PaymentScheduler> schduler= paymentSchDao.findById(schdulerId);
		if(schduler.get()==null) {
			throw new LoanFailureException("Bad Request");
		}
		PaymentScheduler paidState=schduler.get();
		paidState.setStatus("PAID");
		paymentSchDao.save(paidState);
		LocalDate date=paidState.getPaymentDate();
		date=date.plusMonths(1);
		PaymentScheduler changeDate=paymentSchDao.findByLoanAndPaymentDate(paidState.getLoan(),date);
		changeDate.setStatus("AWAITINGPAYMENT");
		paymentSchDao.save(changeDate);
		return paidState;
	}
}

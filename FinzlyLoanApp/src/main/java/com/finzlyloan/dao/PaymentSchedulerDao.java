package com.finzlyloan.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import com.finzlyloan.entity.PaymentScheduler;

public interface PaymentSchedulerDao extends JpaRepository<PaymentScheduler, String> {



	 List<PaymentScheduler> findByLoan(String loanId);

	PaymentScheduler findByLoanAndPaymentDate(String loan, LocalDate date);

}

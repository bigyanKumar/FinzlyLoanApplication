package com.finzlyloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finzlyloan.entity.Customer;
import com.finzlyloan.entity.Loan;

public interface LoanDao extends JpaRepository<Loan, String>{

	List<Loan> findByCustomer(Customer customer);

}

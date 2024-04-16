package com.finzlyloan.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.finzlyloan.entity.Customer;
@Repository
public interface CustomerDao extends JpaRepository<Customer, String>{
	
	Optional<Customer> findByEmailAndPassword(String email,String password);

}

package com.finzlyloan.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Loan {
	
	@Id
	private String id;
	private Double loanAmount;
	private LocalDateTime date;
	private LocalDate loanStartDate;
	private LocalDate maturityDate;
	private String paymentFrequency;
	private String paymentType;
	private Double intrestRate;
	@ManyToOne(targetEntity = Customer.class, cascade = CascadeType.ALL)
	private Customer customer;
}

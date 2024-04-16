package com.finzlyloan.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class PaymentScheduler {
	
	 @Id
	 private String id;
	 private LocalDate paymentDate;
	 private Double principal;
	 private Double projectedIntrest;
	 private String status;
	 private Double amount;
	 private String loan;
	 

}

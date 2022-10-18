package com.finzlyloan.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Customer {
	
	@Id
	private String email;
	private String mobile;
	private String name;
	private String password;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	
	
}

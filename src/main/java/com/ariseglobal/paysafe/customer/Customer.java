package com.ariseglobal.paysafe.customer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String paysafeId;
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", email=" + email + ", paysafeId=" + paysafeId + "]";
	}

}


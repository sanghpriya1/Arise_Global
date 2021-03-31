package com.ariseglobal.paysafe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ariseglobal.paysafe.customer.Customer;



@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{	
	
	Customer findByEmail(String emailId);
	
}


package com.ariseglobal.paysafe.dto;

import com.ariseglobal.paysafe.customer.CustomerDOB;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewPaySafeCustomerResponseDTO {

	private String id;
	private String merchantCustomerId;
	private String locale;
	private String firstName;
	private String middleName;
	private String lastName;
	private CustomerDOB dateOfBirth;
	private String email;
	private String phone;
	private String ip;
	private String gender;
	private String nationality;
	private String cellPhone;
	private String status;
	
	@Override
	public String toString() {
		return "CreateNewPaySafeCustomerResponseDTO [id=" + id + ", merchantCustomerId=" + merchantCustomerId
				+ ", locale=" + locale + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", phone=" + phone + ", ip=" + ip
				+ ", gender=" + gender + ", nationality=" + nationality + ", cellPhone=" + cellPhone + ", status="
				+ status + "]";
	}
	
}


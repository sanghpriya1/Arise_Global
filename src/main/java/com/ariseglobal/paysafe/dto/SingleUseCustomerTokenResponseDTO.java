package com.ariseglobal.paysafe.dto;

import java.util.List;

import com.ariseglobal.paysafe.PaySafePaymentHandle;
import com.ariseglobal.paysafe.customer.CustomerAddress;
import com.ariseglobal.paysafe.customer.CustomerDOB;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SingleUseCustomerTokenResponseDTO {

	private String id;
	private String merchantRefNum;
	private String profileId;
	private Integer timeToLiveSeconds;
	private String status;
	private String singleUseCustomerToken;
	private List<String> paymentTypes;
	private String locale;
	private String firstName;
	private String middleName;
	private String lastName;
	private CustomerDOB dateOfBirth;
	private String email;
	private String phone;
	private String ip;
	private String nationality;
	private List<CustomerAddress> addresses;
	private List<PaySafePaymentHandle> paymentHandles;

	@Override
	public String toString() {
		return "SingleUseCustomerTokenResponseDTO [id=" + id + ", merchantRefNum=" + merchantRefNum + ", profileId="
				+ profileId + ", timeToLiveSeconds=" + timeToLiveSeconds + ", status=" + status
				+ ", singleUseCustomerToken=" + singleUseCustomerToken + ", paymentTypes=" + paymentTypes + ", locale="
				+ locale + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", phone=" + phone + ", ip=" + ip
				+ ", nationality=" + nationality + ", addresses=" + addresses + ", paymentHandles=" + paymentHandles
				+ "]";
	}

}


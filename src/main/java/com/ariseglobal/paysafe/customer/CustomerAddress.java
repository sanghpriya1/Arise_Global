package com.ariseglobal.paysafe.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class CustomerAddress {

	private String id;
	private String nickName;
	private String street;
	private String street2;
	private String city;
	private String state;
	private String country;
	private String zip;
	private String phone;
	private String status;
	private Boolean defaultShippingAddressIndicator;

}


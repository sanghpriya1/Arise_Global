package com.ariseglobal.paysafe.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SingleUseCustomerTokenRequestDTO {

	private String merchantRefNum;
	private List<String> paymentTypes;

	@Override
	public String toString() {
		return "SingleUseCustomerTokenRequestDTO [merchantRefNum=" + merchantRefNum + ", paymentTypes=" + paymentTypes
				+ "]";
	}

}

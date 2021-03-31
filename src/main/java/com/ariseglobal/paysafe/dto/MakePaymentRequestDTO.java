package com.ariseglobal.paysafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MakePaymentRequestDTO {

	private String merchantRefNum;
    private Integer amount;
    private String currencyCode;
    private boolean dupCheck;
    private boolean settleWithAuth;
    private String paymentHandleToken;
    private String customerIp;
    private String description;
    
	@Override
	public String toString() {
		return "MakePaymentRequestDTO [merchantRefNum=" + merchantRefNum + ", amount=" + amount + ", currencyCode="
				+ currencyCode + ", dupCheck=" + dupCheck + ", settleWithAuth=" + settleWithAuth
				+ ", paymentHandleToken=" + paymentHandleToken + ", customerIp=" + customerIp + ", description="
				+ description + "]";
	}    
	
}

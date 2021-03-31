package com.ariseglobal.paysafe.dto;



import com.ariseglobal.paysafe.GatewayResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class MakePaymentResponseDTO {
	
	private String id;
	private Integer amount;
	private String merchantRefNum;
    private Boolean settleWithAuth;
    private String paymentHandleToken;
    private String txnTime;
    private String customerIp;
    private Boolean dupCheck;
    private String description;
    private String currencyCode;
    private String paymentType;
    private String status;
    private Integer availableToSettle;
    private GatewayResponse gatewayResponse;
    private String customerId;
    private String merchantCustomerId;
    
}


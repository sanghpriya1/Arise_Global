package com.ariseglobal.paysafe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class PaySafePaymentHandle {

	private String id;
	private String status;
	private String usage;
	private String paymentType;
	private String paymentHandleToken;
	private String billingDetailsId;
	private Card card;
	
}
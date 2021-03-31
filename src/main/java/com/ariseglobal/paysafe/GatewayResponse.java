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
public class GatewayResponse {
	
	private String authCode;
    private String avsResponse;
    private String cvvVerification;
    
}


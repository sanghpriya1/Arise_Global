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
public class Card {
	
	private String lastDigits;
	private CardExipry cardExpiry;
	private String cardBin;
	private String cardType;
	private String holderName;	
	
}

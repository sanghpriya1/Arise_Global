package com.ariseglobal.paysafe.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDOB {

	private Integer day;
	private Integer month;
	private Integer year;

	@Override
	public String toString() {
		return "CustomerDOB [day=" + day + ", month=" + month + ", year=" + year + "]";
	}
	
}

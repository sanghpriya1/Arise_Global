package com.ariseglobal.paysafe.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaySafeResponseDTO<T> {

	private HttpStatus status;
	private String message;
	private T data;

	@Override
	public String toString() {
		return "PaySafeResponseDTO [status=" + status + ", message=" + message + ", data=" + data + "]";
	}

}

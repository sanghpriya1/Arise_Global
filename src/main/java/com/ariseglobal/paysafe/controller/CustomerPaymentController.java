package com.ariseglobal.paysafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ariseglobal.paysafe.PaySafeCheckoutService;
import com.ariseglobal.paysafe.dto.MakePaymentRequestDTO;
import com.ariseglobal.paysafe.dto.MakePaymentResponseDTO;
import com.ariseglobal.paysafe.dto.PaySafeResponseDTO;
import com.ariseglobal.paysafe.dto.SingleUseCustomerTokenResponseDTO;




@RestController
public class CustomerPaymentController {

	@Autowired
	private PaySafeCheckoutService paySafecheckoutService;

	@GetMapping("/SingleUseCustomerToken/{emailId}")
	public PaySafeResponseDTO getSingleUseCustomerToken(@PathVariable String emailId) {
		PaySafeResponseDTO<SingleUseCustomerTokenResponseDTO> paySafeResponseDTO = new PaySafeResponseDTO<SingleUseCustomerTokenResponseDTO>();
		paySafeResponseDTO.setStatus(HttpStatus.OK);
		paySafeResponseDTO.setMessage("SingleUseCustomerToken Created");
		paySafeResponseDTO.setData(paySafecheckoutService.creatSingleUserCustomerToken(emailId));
		return paySafeResponseDTO;
	}

	@PostMapping("/MakePayment/{emailId}")
	public PaySafeResponseDTO makePayment(@PathVariable String emailId,
			@RequestBody MakePaymentRequestDTO makePaymentRequestDTO) {
		PaySafeResponseDTO<MakePaymentResponseDTO> responseDTO = new PaySafeResponseDTO<MakePaymentResponseDTO>();
		responseDTO.setStatus(HttpStatus.OK);
		responseDTO.setMessage("Payment Done Successfully");
		responseDTO.setData(paySafecheckoutService.makePayment(makePaymentRequestDTO));
		return responseDTO;
	}

}

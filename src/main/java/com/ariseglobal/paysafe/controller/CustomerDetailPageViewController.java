package com.ariseglobal.paysafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerDetailPageViewController {

	@RequestMapping("/")
	public String getCustomerDetailPage() {
		return "CustomerDetailPage";
	}

}
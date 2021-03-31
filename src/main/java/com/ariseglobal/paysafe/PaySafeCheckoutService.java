package com.ariseglobal.paysafe;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ariseglobal.paysafe.customer.Customer;
import com.ariseglobal.paysafe.customer.CustomerDOB;
import com.ariseglobal.paysafe.dto.CreateNewPaySafeCustomerRequestDTO;
import com.ariseglobal.paysafe.dto.CreateNewPaySafeCustomerResponseDTO;
import com.ariseglobal.paysafe.dto.MakePaymentRequestDTO;
import com.ariseglobal.paysafe.dto.MakePaymentResponseDTO;
import com.ariseglobal.paysafe.dto.SingleUseCustomerTokenRequestDTO;
import com.ariseglobal.paysafe.dto.SingleUseCustomerTokenResponseDTO;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PaySafeCheckoutService {

	private Random randomNumber;
	private RestTemplate restTemplate;
	private CustomerRepository customerRepository;

	@Value("${paysafe.url}")
	private String baseUrl;
	@Value("${paysafe.username}")
	private String userName;
	@Value("${paysafe.password}")
	private String password;

	@Autowired
	public PaySafeCheckoutService(CustomerRepository customerRepository, RestTemplate restTemplate) {
		this.customerRepository = customerRepository;
		this.restTemplate = restTemplate;
		randomNumber = new Random();
	}

	public SingleUseCustomerTokenResponseDTO creatSingleUserCustomerToken(String emailId) {

		Customer customer = getCustomerObject(emailId);
		if (customer == null) {
			return null;
		}

		String tokenUrl = getCustomerPaySafeUrl() + "/" + customer.getPaysafeId() + "/singleusecustomertokens";
		HttpHeaders headers = getHttpHeaders();
		SingleUseCustomerTokenRequestDTO singleUseCustomerTokenRequestDTO = getSingleUseCustomerTokenRequestObject();
		String jsonString = getSingleUseCustomerTokenJsonObject(singleUseCustomerTokenRequestDTO);

		// build the request
		HttpEntity<String> entity = new HttpEntity<>(jsonString, headers);
		// send POST request
		ResponseEntity<SingleUseCustomerTokenResponseDTO> responseEntity = restTemplate.postForEntity(tokenUrl, entity,
				SingleUseCustomerTokenResponseDTO.class);

		if (responseEntity.getStatusCode().equals(HttpStatus.CREATED)) {
			SingleUseCustomerTokenResponseDTO singleUseCustomerTokenResponseDTO = responseEntity.getBody();
			singleUseCustomerTokenResponseDTO.setMerchantRefNum(singleUseCustomerTokenRequestDTO.getMerchantRefNum());
			return singleUseCustomerTokenResponseDTO;
		}
		return null;
	}

	private Customer createCustomer(String emailId) {
		String url = getCustomerPaySafeUrl();
		HttpHeaders headers = getHttpHeaders();
		String jsonString = getCustomerJsonObject(getCustomerRequestObject(emailId));
		HttpEntity<String> entity = new HttpEntity<String>(jsonString, headers);
		ResponseEntity<CreateNewPaySafeCustomerResponseDTO> responseEntity = restTemplate.postForEntity(url, entity,
				CreateNewPaySafeCustomerResponseDTO.class);
		if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
			CreateNewPaySafeCustomerResponseDTO response = responseEntity.getBody();
			Customer newCustomer = new Customer();
			newCustomer.setPaysafeId(response.getId());
			newCustomer.setEmail(response.getEmail());
			customerRepository.save(newCustomer);
			return newCustomer;
		} else {
			Customer customer = null;
			return customer;
		}
	}

	public MakePaymentResponseDTO makePayment(MakePaymentRequestDTO makePaymentRequestDTO) {
		String makePaymentUrl = getMakePaymentPaySafeUrl();
		HttpHeaders headers = getHttpHeaders();

		makePaymentRequestDTO.setCustomerIp("10.10.12.64");
		makePaymentRequestDTO.setMerchantRefNum(String.valueOf(randomNumber.nextInt()));
		String jsonString = getMakePaymentRequestJsonObject(makePaymentRequestDTO);

		HttpEntity<String> entity = new HttpEntity<>(jsonString, headers);
		ResponseEntity<MakePaymentResponseDTO> responseEntity = restTemplate.postForEntity(makePaymentUrl, entity,
				MakePaymentResponseDTO.class);

		if (responseEntity.getStatusCode() != HttpStatus.CREATED) {
			// throw an exception
		}
		return responseEntity.getBody();
	}

	private String getMakePaymentRequestJsonObject(MakePaymentRequestDTO makePaymentRequestDTO) {
		// convert request object into JSON object
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = objectMapper.writeValueAsString(makePaymentRequestDTO);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonString;
	}

	private String getSingleUseCustomerTokenJsonObject(
			SingleUseCustomerTokenRequestDTO singleUseCustomerTokenRequestDTO) {
		// convert request object into JSON object
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = objectMapper.writeValueAsString(singleUseCustomerTokenRequestDTO);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonString;
	}

	private String getCustomerJsonObject(CreateNewPaySafeCustomerRequestDTO createNewPaySafeCustomerRequestDTO) {
		// convert request object into JSON object
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = objectMapper.writeValueAsString(createNewPaySafeCustomerRequestDTO);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonString;
	}

	private HttpHeaders getHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setBasicAuth(userName, password);
		return headers;
	}

	private SingleUseCustomerTokenRequestDTO getSingleUseCustomerTokenRequestObject() {
		SingleUseCustomerTokenRequestDTO singleUseCustomerTokenRequestDTO = new SingleUseCustomerTokenRequestDTO();
		singleUseCustomerTokenRequestDTO.setMerchantRefNum(String.valueOf(randomNumber.nextInt()));
		singleUseCustomerTokenRequestDTO.setPaymentTypes(Arrays.asList("CARD"));
		return singleUseCustomerTokenRequestDTO;
	}

	private CreateNewPaySafeCustomerRequestDTO getCustomerRequestObject(String emailId) {
		CreateNewPaySafeCustomerRequestDTO createNewCustomerRequestDTO = new CreateNewPaySafeCustomerRequestDTO();
		createNewCustomerRequestDTO.setMerchantCustomerId(String.valueOf(randomNumber.nextInt()));
		createNewCustomerRequestDTO.setLocale("en_US");
		createNewCustomerRequestDTO.setFirstName("abc");
		createNewCustomerRequestDTO.setMiddleName("pqr");
		createNewCustomerRequestDTO.setLastName("xyz");
		createNewCustomerRequestDTO.setDateOfBirth(getCustomerDOB());
		createNewCustomerRequestDTO.setEmail(emailId);
		createNewCustomerRequestDTO.setCellPhone("9056482124");
		createNewCustomerRequestDTO.setGender("M");
		createNewCustomerRequestDTO.setNationality("Canadian");
		createNewCustomerRequestDTO.setPhone("777-444-8888");
		createNewCustomerRequestDTO.setIp("192.0.126.111");
		return createNewCustomerRequestDTO;
	}

	private Customer getCustomerObject(String emailId) {
		Customer customer = null;
		customer = customerRepository.findByEmail(emailId);
		if (customer == null) {
			customer = createCustomer(emailId);
		}
		return customer;
	}

	private CustomerDOB getCustomerDOB() {
		CustomerDOB customerDOB = new CustomerDOB(20, 9, 1988);
		return customerDOB;
	}

	private String getMakePaymentPaySafeUrl() {
		String makePaymentUrl = baseUrl + "/payments";
		return makePaymentUrl;
	}

	private String getCustomerPaySafeUrl() {
		String customerPaySafeUrl = baseUrl + "/customers";
		return customerPaySafeUrl;
	}

}


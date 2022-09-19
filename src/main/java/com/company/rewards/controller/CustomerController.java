package com.company.rewards.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.rewards.dto.CustomerDto;
import com.company.rewards.dto.Response;
import com.company.rewards.dto.Rewards;
import com.company.rewards.dto.TransactionsDto;
import com.company.rewards.exceptions.ApplicationException;
import com.company.rewards.service.CustomerService;
import com.company.rewards.utils.CollectionUtils;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("customer")
@Slf4j
@Tag(name = "Customer Service", description = "API to perform actions on Customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAll(HttpServletRequest httpServletRequest) throws Exception{
		try {
			List<Rewards> customers = customerService.getAll();
			if(CollectionUtils.isEmpty(customers)) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			return ResponseEntity.status(HttpStatus.OK).body(new Response(customers));
		}catch(ApplicationException ap) {
			log.error("Failed to get customer list, failure reason {} ", ap);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ap.getErrorMsg()); 
		}
	}
	
	
	@GetMapping(value="/{customerName:.+}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCustomer(HttpServletRequest httpServletRequest,@PathVariable("customerName") String customerName) throws Exception{
		try {
			log.info("Request received to get Customer Rewards for  {} ",customerName);
			List<Rewards> customers = customerService.getRewardsByUser(customerName);
			if(CollectionUtils.isEmpty(customers)) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			return ResponseEntity.status(HttpStatus.OK).body(new Response(customers));
		}catch(ApplicationException ap) {
			log.error("Failed to get customer list, failure reason {} ", ap);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ap.getErrorMsg()); 
		}
	}
	
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody CustomerDto customerDto, HttpServletRequest httpServletRequest) throws Exception{
		try {
			log.info("Request received to create a customer with name {} ",customerDto.getName());
			 customerService.createCustomer(customerDto);		
		}catch(ApplicationException ap) {
			log.error("Failed to save the customer, failure reason {}",ap);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ap.getErrorMsg()); 
		}
		return new ResponseEntity<Response>(HttpStatus.OK);
	}
	
	@PostMapping(value="/transactions",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createTransaction(@RequestParam(required = true) String customerName,
									@RequestBody TransactionsDto transactionsDto, HttpServletRequest httpServletRequest) throws Exception{
		
		try {
			log.info("Request received to create a customer Transactions for {} ",customerName);
			customerService.createCustomerTransactions(customerName,transactionsDto);	
		}catch(ApplicationException ap) {
			log.error("Failed to save the customer transactions , failure reason {}",ap);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ap.getErrorMsg()); 
		}
		return new ResponseEntity<Response>(HttpStatus.OK);
	}
}

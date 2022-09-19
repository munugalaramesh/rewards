package com.company.rewards.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.rewards.dto.CustomerDto;
import com.company.rewards.dto.Rewards;
import com.company.rewards.dto.TransactionsDto;
import com.company.rewards.entity.Customer;
import com.company.rewards.entity.CustomerTransactions;
import com.company.rewards.exceptions.ApplicationException;
import com.company.rewards.repository.CustomerRepository;
import com.company.rewards.repository.TransactionsRepository;
import com.company.rewards.utils.CollectionUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private RewardsService rewardsService;
	
	@Autowired
	private TransactionsRepository transactionsRepository;

	public List<Rewards> getAll() throws ApplicationException {
		List<Rewards> rewards = mapToReward(customerRepository.groupByMonthAndCustomer());
		Map<String, Map<String, List<Rewards>>> groupBy = CollectionUtils.asStream(rewards).collect(Collectors.groupingBy(Rewards :: getName,
													        Collectors.groupingBy(Rewards :: getGroupByYear)));
		rewards.forEach(f -> f.setYearlyRewards(getYearCount(f,groupBy)));
		return rewards;
	}
	
	public List<Rewards> getRewardsByUser(String customerName) {
		List<Rewards> rewards = mapToReward(customerRepository.groupByMonthAndCustomerUsingCustomerName(customerName));
		Map<String, Map<String, List<Rewards>>> groupBy = CollectionUtils.asStream(rewards).collect(Collectors.groupingBy(Rewards :: getName,
		        Collectors.groupingBy(Rewards :: getGroupByYear)));
		rewards.forEach(f -> f.setYearlyRewards(getYearCount(f,groupBy)));
		return rewards;
	}
	
	public BigDecimal getYearCount(Rewards reward,Map<String, Map<String, List<Rewards>>> groupBy) {
		return CollectionUtils.asStream(groupBy.get(reward.getName()).get(reward.getGroupByYear())).map(r ->r.rewards).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
	}

	private List<Rewards> mapToReward(List<Object[]> objects) {
		List<Rewards> rewards = new ArrayList<Rewards>();
		if(CollectionUtils.isEmpty(objects)) return rewards;
		if(objects != null && !objects.isEmpty()){
	          for (Object[] object : objects) {
	        	  Rewards reward = new Rewards();
	        	  reward.setRewards((BigDecimal)object[0]);
	        	  reward.setName((String)object[1]);
	        	  reward.setGroupByMonth((String)object[2]);
	        	  reward.setGroupByYear(String.valueOf((Integer)object[3]));
	        	  rewards.add(reward);
	          }
	       }
		return rewards;
	}

	

	public void createCustomer(CustomerDto customerDto) throws ApplicationException{
		Customer dbCustomer = customerRepository.findByname(customerDto.getName());
		if(dbCustomer!=null) {
			throw new ApplicationException("CUSTOMER_ALREADY_EXIST", "Customer Already Exist");
		}else {
			Customer customer = new Customer();
			customer.setName(customerDto.getName());
			customer.setCreatedOn(new Date());
			try {
				customerRepository.save(customer);
			} catch (RuntimeException re) {
				log.error("Failed to save the user, reason {} ",re);
				throw new ApplicationException("CUSTOMER_SAVE_DB_FAILED","Failed to Save Customer");
			}
		}
	}
	
	
	public void createCustomerTransactions(String customerName,TransactionsDto transactionDto) throws ApplicationException{
		Customer customer = customerRepository.findByname(customerName);
		if(customer==null) {
			throw new ApplicationException("CUSTOMER_NOT_EXIST", "Customer Doesn't Exist");
		}
		CustomerTransactions custTrans = new CustomerTransactions();
		custTrans.setAmount(transactionDto.getAmount());
		custTrans.setCreatedOn(transactionDto.getTransactionDate());
		custTrans.setCustomer(customer);
		custTrans.setRewards(rewardsService.calRenewardsPerTx(transactionDto.getAmount()));
		try {
			transactionsRepository.save(custTrans);
		} catch (RuntimeException re) {
			log.error("Failed to save the Transactions, reason {} ",re);
			throw new ApplicationException("TRANSACTION_SAVE_DB_FAILED","Failed to Save Customer Transactions");
		}
	}

	

}

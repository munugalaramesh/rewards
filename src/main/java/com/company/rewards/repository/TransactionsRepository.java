package com.company.rewards.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.rewards.entity.CustomerTransactions;

@Repository
public interface TransactionsRepository extends CrudRepository<CustomerTransactions, Long>{
	
	

}

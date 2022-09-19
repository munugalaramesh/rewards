package com.company.rewards.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class RewardsService {
	
	
	public BigDecimal calRenewardsPerTx(BigDecimal amount) {
		
		BigDecimal rewardpoints = BigDecimal.ZERO;
		
		if (amount != null) {
			if (amount.compareTo(new BigDecimal(50)) > 0 && amount.compareTo(new BigDecimal(100)) <= 0) {
				rewardpoints = amount.subtract(new BigDecimal(50));
			}
			if (amount.compareTo(new BigDecimal(100)) > 0) {
				rewardpoints = amount.subtract(new BigDecimal(100)).multiply(new BigDecimal(2));
			}
			if (amount.compareTo(new BigDecimal(100)) > 0 && amount.compareTo(new BigDecimal(50)) >= 0) {
				rewardpoints = rewardpoints.add(new BigDecimal(50));
			}
		}
		return rewardpoints;
	}

}

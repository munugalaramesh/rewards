package com.company.rewards.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class Rewards implements Serializable{
	
	private static final long serialVersionUID = 5156104522654948954L;

	public String name;
	
	public String groupByMonth;
	
	public String groupByYear;
	
	public BigDecimal rewards;
	
	public BigDecimal totalRewards;
	
	public BigDecimal yearlyRewards;
	

	public Rewards(String name, String groupByMonth, String groupByYear, BigDecimal rewards, BigDecimal totalRewards,
			BigDecimal yearlyRewards) {
		super();
		this.name = name;
		this.groupByMonth = groupByMonth;
		this.groupByYear = groupByYear;
		this.rewards = rewards;
		this.totalRewards = totalRewards;
		this.yearlyRewards = yearlyRewards;
	}

	

	public Rewards(String name, String groupByMonth, String groupByYear, BigDecimal rewards) {
		super();
		this.name = name;
		this.groupByMonth = groupByMonth;
		this.groupByYear = groupByYear;
		this.rewards = rewards;
	}

	public Rewards() {
		super();
	}

	public Rewards(String name, String groupByMonth, String groupByYear, BigDecimal rewards, BigDecimal yearlyRewards) {
		super();
		this.name = name;
		this.groupByMonth = groupByMonth;
		this.groupByYear = groupByYear;
		this.rewards = rewards;
		this.yearlyRewards = yearlyRewards;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroupByMonth() {
		return groupByMonth;
	}

	public void setGroupByMonth(String groupByMonth) {
		this.groupByMonth = groupByMonth;
	}

	public String getGroupByYear() {
		return groupByYear;
	}

	public void setGroupByYear(String groupByYear) {
		this.groupByYear = groupByYear;
	}

	public BigDecimal getRewards() {
		return rewards;
	}

	public void setRewards(BigDecimal rewards) {
		this.rewards = rewards;
	}

	public BigDecimal getYearlyRewards() {
		return yearlyRewards;
	}

	public void setYearlyRewards(BigDecimal yearlyRewards) {
		this.yearlyRewards = yearlyRewards;
	}
	
	public BigDecimal getTotalRewards() {
		return totalRewards;
	}

	public void setTotalRewards(BigDecimal totalRewards) {
		this.totalRewards = totalRewards;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groupByMonth == null) ? 0 : groupByMonth.hashCode());
		result = prime * result + ((groupByYear == null) ? 0 : groupByYear.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((rewards == null) ? 0 : rewards.hashCode());
		result = prime * result + ((yearlyRewards == null) ? 0 : yearlyRewards.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rewards other = (Rewards) obj;
		if (groupByMonth == null) {
			if (other.groupByMonth != null)
				return false;
		} else if (!groupByMonth.equals(other.groupByMonth))
			return false;
		if (groupByYear == null) {
			if (other.groupByYear != null)
				return false;
		} else if (!groupByYear.equals(other.groupByYear))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rewards == null) {
			if (other.rewards != null)
				return false;
		} else if (!rewards.equals(other.rewards))
			return false;
		if (yearlyRewards == null) {
			if (other.yearlyRewards != null)
				return false;
		} else if (!yearlyRewards.equals(other.yearlyRewards))
			return false;
		return true;
	}
	
	

}

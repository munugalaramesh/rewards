package com.company.rewards.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Transactions")
@Data
@NoArgsConstructor
@Builder(toBuilder = true)
@AllArgsConstructor
@EqualsAndHashCode
public class CustomerTransactions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@EqualsAndHashCode.Exclude
	@ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
	
	@EqualsAndHashCode.Exclude
	@Temporal(TemporalType.DATE)
	@Column(name = "createdOn", length = 23)
	private Date createdOn;
	
	@EqualsAndHashCode.Exclude
	@Column(name="amount", nullable=false)
	private BigDecimal amount;
	
	@EqualsAndHashCode.Exclude
	@Column(name="rewards", nullable=false)
	private BigDecimal rewards;
	
}

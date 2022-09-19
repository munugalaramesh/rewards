package com.company.rewards.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Customer")
@Data
@NoArgsConstructor
@Builder(toBuilder = true)
@AllArgsConstructor
@EqualsAndHashCode
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	private long id;
	
	@EqualsAndHashCode.Exclude
	@Column(name = "name")
	private String name;
	
	@EqualsAndHashCode.Exclude
	@Temporal(TemporalType.DATE)
	@Column(name = "createdOn", length = 23)
	private Date createdOn;
	
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy="customer",cascade = CascadeType.ALL,orphanRemoval = true )
    List<CustomerTransactions> customerTransactions ;
	
	

}

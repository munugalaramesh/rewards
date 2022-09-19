package com.company.rewards.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.rewards.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{
	
	List<Customer> findAll();
	
	Customer findById(long id);
	
	Customer findByname(String name);
	
	@Query(nativeQuery = true, value=" SELECT SUM(r.REWARDS)  as rewards , C.name as name, MONTHNAME(r.CREATED_ON) as groupByMonth , EXTRACT(YEAR FROM r.CREATED_ON) as groupByYear FROM TRANSACTIONS  r "
			+ " left join CUSTOMER c on c.id = r.customer_id GROUP BY c.name,MONTHNAME(r.CREATED_ON), EXTRACT(YEAR FROM r.CREATED_ON)")
	List<Object[]> groupByMonthAndCustomer();
	
	
	@Query(nativeQuery = true, value=" SELECT SUM(r.REWARDS)  as rewards , C.name as name, MONTHNAME(r.CREATED_ON) as groupByMonth , EXTRACT(YEAR FROM r.CREATED_ON) as groupByYear FROM TRANSACTIONS  r "
			+ " left join CUSTOMER c on c.id = r.customer_id  where c.name =:customerName GROUP BY c.name,MONTHNAME(r.CREATED_ON), EXTRACT(YEAR FROM r.CREATED_ON)")
	List<Object[]> groupByMonthAndCustomerUsingCustomerName(@Param("customerName") String customerName);

}

package com.lautarocutri.dev.mareoenvios.customer.repo;

import java.util.List;

import com.lautarocutri.dev.mareoenvios.customer.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	List<Customer> findAllByFirstNameAndLastName(String name, String lastName);
}

package com.lautarocutri.dev.mareoenvios.customer.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.lautarocutri.dev.mareoenvios.customer.entity.Customer;
import com.lautarocutri.dev.mareoenvios.customer.jax.CustomerJax;
import com.lautarocutri.dev.mareoenvios.customer.mapper.CustomerMapper;
import com.lautarocutri.dev.mareoenvios.customer.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerMapper customerMapper;

	public List<CustomerJax> getAllCustomers() {
		return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
				.map(customer -> customerMapper.toDto(customer))
				.collect(Collectors.toList());
	}

	public List<CustomerJax> getAllCustomersByNameAndLastName(String name, String lastName) {
		return StreamSupport.stream(
						customerRepository.findAllByFirstNameAndLastName(name, lastName).spliterator(),
						false)
				.map(customer -> customerMapper.toDto(customer))
				.collect(Collectors.toList());
	}

	public CustomerJax getCustomerById(Integer Id) {
		return customerMapper.toDto(customerRepository.findById(Id).orElseThrow(() -> new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Customer not found")));
	}

	public CustomerJax createCustomer(Customer customer) {
		return customerMapper.toDto(customerRepository.save(customer));
	}
}

package com.lautarocutri.dev.mareoenvios.customer.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.lautarocutri.dev.mareoenvios.customer.entity.Customer;
import com.lautarocutri.dev.mareoenvios.customer.jax.CustomerJax;
import com.lautarocutri.dev.mareoenvios.customer.jax.CustomerSaveJax;
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
		var customerList = StreamSupport.stream(customerRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return customerMapper.toDtoList(customerList);
	}

	public List<CustomerJax> getAllCustomersByNameAndLastName(String name, String lastName) {
		var customerList = StreamSupport.stream(
						customerRepository.findAllByFirstNameAndLastName(name, lastName).spliterator(),
						false)
				.collect(Collectors.toList());
		return customerMapper.toDtoList(customerList);
	}

	public CustomerJax getCustomerById(Integer Id) {
		var customer = customerRepository.findById(Id).orElseThrow(() -> new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Customer not found"));
		return customerMapper.toDto(customer);
	}

	public CustomerJax createCustomer(CustomerSaveJax customer) {
		return customerMapper.toDto(customerRepository.save(Customer.createNew(
				customer.getFirstName(),
				customer.getLastName(),
				customer.getAddress(),
				customer.getCity()
		)));
	}
}

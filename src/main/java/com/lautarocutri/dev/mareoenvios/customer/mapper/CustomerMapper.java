package com.lautarocutri.dev.mareoenvios.customer.mapper;

import java.util.List;

import com.lautarocutri.dev.mareoenvios.customer.entity.Customer;
import com.lautarocutri.dev.mareoenvios.customer.jax.CustomerJax;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
		componentModel = "spring",
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface CustomerMapper {
	CustomerJax toDto(Customer customerEntity);

	List<CustomerJax> toDtoList(List<Customer> userEntities);
}

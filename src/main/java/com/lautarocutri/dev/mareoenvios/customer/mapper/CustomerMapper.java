package com.lautarocutri.dev.mareoenvios.customer.mapper;

import com.lautarocutri.dev.mareoenvios.customer.entity.Customer;
import com.lautarocutri.dev.mareoenvios.customer.jax.CustomerJax;
import com.lautarocutri.dev.mareoenvios.customer.jax.CustomerSaveJax;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
		componentModel = "spring",
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface CustomerMapper {
	CustomerJax toDto(Customer customerEntity);
	Customer toEntity(CustomerSaveJax customerJax);
}

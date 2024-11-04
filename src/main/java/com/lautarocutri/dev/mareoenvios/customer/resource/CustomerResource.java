package com.lautarocutri.dev.mareoenvios.customer.resource;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.lautarocutri.dev.mareoenvios.customer.jax.CustomerJax;
import com.lautarocutri.dev.mareoenvios.customer.jax.CustomerSaveJax;
import com.lautarocutri.dev.mareoenvios.customer.mapper.CustomerMapper;
import com.lautarocutri.dev.mareoenvios.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerResource {

	private final CustomerMapper customerMapper;
	private final CustomerService customerService;

	public CustomerResource(CustomerMapper customerMapper, CustomerService customerService) {
		this.customerMapper = customerMapper;
		this.customerService = customerService;
	}

	@Operation(summary = "Get all customers", description = "Returns a list of all customers")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Successful operation",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = CustomerJax.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error",
					content = @Content),
			@ApiResponse(responseCode = "403", description = "Unathorized",
					content = @Content)
	})
	@GetMapping
	public ResponseEntity<List<CustomerJax>> getAllCustomers(
			@Nullable @RequestParam(required = false) String name,
			@Nullable @RequestParam(required = false) String lastName
	) {
		if (name != null && lastName != null) {
			return getAllCustomersByNameAndLastName(name, lastName);
		}
		return ResponseEntity.ok(customerService.getAllCustomers());
	}

	private ResponseEntity<List<CustomerJax>> getAllCustomersByNameAndLastName(
			@Nullable String name,
			@Nullable String lastName
	) {
		return ResponseEntity.ok(customerService.getAllCustomersByNameAndLastName(name, lastName));
	}

	@Operation(summary = "Get customer by Id", description = "Returns a single customer by it's Id")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Successful operation",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = CustomerJax.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error",
					content = @Content),
			@ApiResponse(responseCode = "403", description = "Unathorized",
					content = @Content)
	})
	@GetMapping("/{id}")
	public ResponseEntity<CustomerJax> getCustomerById(
			@NonNull @NotNull @PathVariable Integer id
	) {
		return ResponseEntity.ok(customerService.getCustomerById(id));
	}

	@Operation(summary = "Create new customer", description = "Creates a new customer")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Successful operation",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = CustomerJax.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error",
					content = @Content),
			@ApiResponse(responseCode = "403", description = "Unathorized",
					content = @Content)
	})
	@PostMapping
	public ResponseEntity<CustomerJax> createCustomer(
			@Valid @NonNull @NotNull @RequestBody CustomerSaveJax customer
	) {
		return ResponseEntity.ok(customerService.createCustomer(customerMapper.toEntity(customer)));
	}
}

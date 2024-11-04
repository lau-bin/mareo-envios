package com.lautarocutri.dev.mareoenvios.customer.jax;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.nullness.qual.NonNull;

@Getter
@Setter
@AllArgsConstructor
public class CustomerJax {

	@NotNull
	@NonNull
	private Integer id;

	@NotNull
	@NonNull
	private String firstName;

	@NotNull
	@NonNull
	private String lastName;

	@NotNull
	@NonNull
	private String address;

	@NotNull
	@NonNull
	private String city;
}

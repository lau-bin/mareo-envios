package com.lautarocutri.dev.mareoenvios.customer.jax;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.nullness.qual.NonNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSaveJax {
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

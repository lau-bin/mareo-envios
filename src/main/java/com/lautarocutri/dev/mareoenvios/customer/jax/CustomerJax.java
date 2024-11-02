package com.lautarocutri.dev.mareoenvios.customer.jax;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.nullness.qual.NonNull;

@Getter
@Setter
@AllArgsConstructor
public class CustomerJax {

	@NotNull
	@Schema(required = true)
	private Integer id;

	@NotNull
	@NonNull
	@Schema(required = true)
	private String firstName;

	@NotNull
	@NonNull
	@Schema(required = true)
	private String lastName;

	@NotNull
	@NonNull
	@Schema(required = true)
	private String address;

	@NotNull
	@NonNull
	@Schema(required = true)
	private String city;
}

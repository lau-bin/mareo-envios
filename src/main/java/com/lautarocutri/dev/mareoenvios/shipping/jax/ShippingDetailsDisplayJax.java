package com.lautarocutri.dev.mareoenvios.shipping.jax;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.nullness.qual.NonNull;

@Getter
@Setter
@AllArgsConstructor
public class ShippingDetailsDisplayJax {

	@NotNull
	@NonNull
	private Integer customerId;

	@NotNull
	@NonNull
	private String state;

	@NotNull
	@NonNull
	private LocalDate sendDate;

	@NotNull
	@NonNull
	private LocalDate arriveDate;

	@NotNull
	@NonNull
	private Integer priority;
}

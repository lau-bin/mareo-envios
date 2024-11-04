package com.lautarocutri.dev.mareoenvios.statistic.jax;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.nullness.qual.NonNull;

@Getter
@Setter
@AllArgsConstructor
public class ProductDescription {

	@NotNull
	@NonNull
	private int quantity;

	@NotNull
	@NonNull
	private String description;
}

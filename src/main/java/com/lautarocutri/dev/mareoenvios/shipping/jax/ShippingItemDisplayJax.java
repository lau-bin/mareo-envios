package com.lautarocutri.dev.mareoenvios.shipping.jax;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.nullness.qual.NonNull;

@Getter
@Setter
@AllArgsConstructor
public class ShippingItemDisplayJax {

	@NotNull
	@NonNull
	private Integer productCount;

	@NotNull
	@NonNull
	private String productDescription;

	@NotNull
	@NonNull
	private Long productWeight;
}

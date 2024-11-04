package com.lautarocutri.dev.mareoenvios.shipping.jax;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.nullness.qual.NonNull;

@Getter
@Setter
@AllArgsConstructor
public class ShippingDisplayJax {

	@NotNull
	@NonNull
	private ShippingDetailsDisplayJax shippingDetails;

	@NotNull
	@NonNull
	private List<ShippingItemDisplayJax> items;
}

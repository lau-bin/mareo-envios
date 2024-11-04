package com.lautarocutri.dev.mareoenvios.statistic.jax;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.nullness.qual.NonNull;

@Getter
@Setter
@AllArgsConstructor
public class MostPopularShippingsDisplayJax {

	@NotNull
	@NonNull
	private List<ProductDescription> products;

}

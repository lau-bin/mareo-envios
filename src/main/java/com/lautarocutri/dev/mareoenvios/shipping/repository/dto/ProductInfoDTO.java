package com.lautarocutri.dev.mareoenvios.shipping.repository.dto;

import javax.validation.constraints.NotNull;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import org.checkerframework.checker.nullness.qual.NonNull;

@Getter
public class ProductInfoDTO {

	private final Integer productCount;
	private final String description;

	@QueryProjection
	public ProductInfoDTO(
			@NotNull Integer productCount,
			@NonNull String description
	){
		this.productCount = productCount;
		this.description = description;
	}
}

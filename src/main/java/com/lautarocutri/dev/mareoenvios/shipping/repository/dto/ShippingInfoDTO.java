package com.lautarocutri.dev.mareoenvios.shipping.repository.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import org.checkerframework.checker.nullness.qual.NonNull;

@Getter
public class ShippingInfoDTO {

	private final Integer shippingId;
	private final Integer customerId;
	private final String state;
	private final LocalDate sendDate;
	private final LocalDate arriveDate;
	private final Integer priority;
	private final Integer productCount;
	private final String description;
	private final Long weight;

	@QueryProjection
	public ShippingInfoDTO(
			@NotNull Integer shippingId,
			@NotNull Integer customerId,
			@NonNull String state,
			@NotNull LocalDate sendDate,
			@NotNull LocalDate arriveDate,
			@NotNull Integer priority,
			@NotNull Integer productCount,
			@NonNull String description,
			@NotNull Long weight
	){
		this.shippingId = shippingId;
		this.customerId = customerId;
		this.state = state;
		this.sendDate = sendDate;
		this.arriveDate = arriveDate;
		this.priority = priority;
		this.productCount = productCount;
		this.description = description;
		this.weight = weight;
	}
}

package com.lautarocutri.dev.mareoenvios.shipping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.lautarocutri.dev.mareoenvios.database.AuditedEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.nullness.qual.NonNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class ShippingItem extends AuditedEntity {

	@NotNull
	@Column(nullable = false)
	private Integer shippingId;

	@NotNull
	@Column(nullable = false)
	private Integer productId;

	@NotNull
	@NonNull
	@Column(nullable = false)
	private Integer productCount;
}

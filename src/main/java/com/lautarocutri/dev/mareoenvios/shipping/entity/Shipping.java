package com.lautarocutri.dev.mareoenvios.shipping.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.lautarocutri.dev.mareoenvios.database.AuditedEntity;
import com.lautarocutri.dev.mareoenvios.database.DBConst;
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
public class Shipping extends AuditedEntity {

	@NotNull
	@Column(nullable = false)
	private Integer customerId;

	@NotNull
	@NonNull
	@Column(nullable = false, length = DBConst.DB_ENUM_LENGTH)
	private State state;

	@NotNull
	@NonNull
	@Column(nullable = false)
	private LocalDate sendDate;

	@NotNull
	@NonNull
	@Column(nullable = false)
	private LocalDate arriveDate;

	@NotNull
	@NonNull
	@Column(nullable = false)
	private Integer priority;

	public enum State {
		INITIAL,
		DELIVERED,
		DELIVERED_TO_POST_OFFICE,
		CANCELED,
		IN_TRANSIT
	}
}

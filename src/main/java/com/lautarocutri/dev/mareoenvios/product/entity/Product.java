package com.lautarocutri.dev.mareoenvios.product.entity;

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
public class Product extends AuditedEntity {

	@NotNull
	@NonNull
	@Column(nullable = false, length = DBConst.DB_STRING_LENGTH)
	private String description;

	@NotNull
	@NonNull
	@Column(nullable = false)
	private Long weight;
}

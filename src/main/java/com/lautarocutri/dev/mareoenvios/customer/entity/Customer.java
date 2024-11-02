package com.lautarocutri.dev.mareoenvios.customer.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.lautarocutri.dev.mareoenvios.database.AuditedEntity;
import com.lautarocutri.dev.mareoenvios.database.DBConst;
import com.lautarocutri.dev.mareoenvios.shipping.entity.ShippingItem;
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
public class Customer extends AuditedEntity {

	@NotNull
	@NonNull
	@Column(nullable = false, length = DBConst.DB_STRING_LENGTH)
	private String firstName;

	@NotNull
	@NonNull
	@Column(nullable = false, length = DBConst.DB_STRING_LENGTH)
	private String lastName;

	@NotNull
	@NonNull
	@Column(nullable = false, length = DBConst.DB_STRING_LENGTH)
	private String address;

	@NotNull
	@NonNull
	@Column(nullable = false, length = DBConst.DB_STRING_LENGTH)
	private String city;
}

package com.lautarocutri.dev.mareoenvios.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RevisionEntity(UserRevisionListener.class)
@Table(name = "REVINFO")
public class UserRevisionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@RevisionNumber
	@Column(name = "REV")
	private int rev;

	@RevisionTimestamp
	@Column(name = "REVTSTMP")
	private long timestamp;

}

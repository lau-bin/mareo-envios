package com.lautarocutri.dev.mareoenvios.database;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.hibernate.envers.RevisionListener;

public class UserRevisionListener implements RevisionListener {
	@Override
	public void newRevision(Object revisionEntity) {
		UserRevisionEntity userRevisionEntity = (UserRevisionEntity) revisionEntity;

		userRevisionEntity.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
	}
}

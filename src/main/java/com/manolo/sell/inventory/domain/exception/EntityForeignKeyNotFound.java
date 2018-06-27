package com.manolo.sell.inventory.domain.exception;

public class EntityForeignKeyNotFound extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7783613716039381500L;

	public EntityForeignKeyNotFound(String msg) {
		super(msg);
	}

}

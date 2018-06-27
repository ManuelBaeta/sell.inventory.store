package com.manolo.sell.inventory.domain.exception;

public class EntityNotFoundException extends BusinessException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5582069009288236518L;

	public EntityNotFoundException(String msg) {
		super(msg);
	}
	
}

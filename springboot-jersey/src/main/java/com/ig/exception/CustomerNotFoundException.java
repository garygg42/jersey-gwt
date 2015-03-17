package com.ig.exception;

import javax.ws.rs.core.Response.Status;

public class CustomerNotFoundException extends ApplicationException {

	public CustomerNotFoundException(Long id) {
		super(Status.NOT_FOUND, String.format(
				"Customer with id: %d does not exist.", id));
	}
}

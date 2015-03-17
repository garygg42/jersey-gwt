package com.ig.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ApplicationExceptionMapper implements
		ExceptionMapper<ApplicationException> {

	@Override
	public Response toResponse(ApplicationException exception) {
		return Response.status(exception.getStatus())
				.entity(exception.getEntity()).build();
	}
}
package com.ig.exception;

import javax.ws.rs.core.Response.Status;

public class ApplicationException extends RuntimeException {

	private class Entity {
		private int status;
		private String message;

		public Entity(int status, String message) {
			super();
			this.status = status;
			this.message = message;
		}

		public int getStatus() {
			return status;
		}

		public String getMessage() {
			return message;
		}
	}

	private final Entity entity;

	public ApplicationException(Status status, String message) {
		this(status.getStatusCode(), message);
	}

	public ApplicationException(int status, String message) {
		super(message);
		entity = new Entity(status, message);
	}

	public Entity getEntity() {
		return entity;
	}

	public int getStatus() {
		return entity.getStatus();
	}

	public String getMessage() {
		return entity.getMessage();
	}

}

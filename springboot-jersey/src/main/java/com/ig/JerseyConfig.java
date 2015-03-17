package com.ig;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.ig.exception.ApplicationExceptionMapper;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		packages("com.ig.rest");
		register(LoggingFilter.class);
		register(ApplicationExceptionMapper.class);
	}
}
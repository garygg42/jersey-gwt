package com.ig.rest;

import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.test.JerseyTest;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ig.config.JerseyConfig;
import com.ig.config.TestConfig;

public class RestResourceTest extends JerseyTest {

	@Override
	protected Application configure() {
		ApplicationContext context = new AnnotationConfigApplicationContext(
				TestConfig.class);
		return new JerseyConfig().property("contextConfig", context);
	}

	@Test
	public void testHello() {
		final String hello = target("hello").request().get(String.class);
		assertThat(hello).isEqualTo("Hello World");
	}

	@Test
	public void testMessages() throws JSONException {
		final String messages = target("messages").request().get(String.class);
		String expected = "[ " + "{ 'author': 'Joe', 'contents': 'Hello'},"
				+ "{ 'author': 'Jane', 'contents': 'Spring boot is cool !'}"
				+ "]";
		JSONAssert.assertEquals(expected, messages, JSONCompareMode.LENIENT);
	}
}

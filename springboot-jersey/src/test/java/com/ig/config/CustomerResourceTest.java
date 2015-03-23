package com.ig.config;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.test.JerseyTest;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ig.JerseyConfig;

public class CustomerResourceTest extends JerseyTest {

	@Override
	protected Application configure() {
		ApplicationContext context = new AnnotationConfigApplicationContext(
				TestConfig.class);
		return new JerseyConfig().property("contextConfig", context);
	}

	@Test
	public void testFindCustomerWithId1() throws JSONException {
		String customer = target("customers").path("1").request()
				.get(String.class);
		String expected = "{\"firstName\":\"Jack\",\"lastName\":\"Bauer\"}";
		JSONAssert.assertEquals(expected, customer, JSONCompareMode.LENIENT);
	}

	@Test
	public void testFindJackBauers() throws JSONException {
		String customers = target("customers").queryParam("firstName", "Jack")
				.queryParam("lastName", "Bauer").request().get(String.class);
		String expected = "[{\"firstName\":\"Jack\",\"lastName\":\"Bauer\"},{\"firstName\":\"Jack\",\"lastName\":\"Bauer\"}]";
		JSONAssert.assertEquals(expected, customers, JSONCompareMode.LENIENT);
	}
}

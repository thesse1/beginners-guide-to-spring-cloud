package com.example.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 * @author Ryan Baxter
 */
public class GreetingServiceTest {

	private GreetingService greetingService;

	@BeforeEach
	public void setup() {
		RestTemplate rest = mock(RestTemplate.class);
		doReturn("Hello").when(rest).getForObject(eq("http://localhost:9090"), eq(String.class));
		doReturn("Hello").when(rest).getForObject(eq("http://localhost:9090/en"), eq(String.class));
		doReturn("Hola").when(rest).getForObject(eq("http://localhost:9090/es"), eq(String.class));
		doReturn("Hello").when(rest).getForObject(eq("http://host.docker.internal:9090"), eq(String.class));
		doReturn("Hello").when(rest).getForObject(eq("http://host.docker.internal:9090/en"), eq(String.class));
		doReturn("Hola").when(rest).getForObject(eq("http://host.docker.internal:9090/es"), eq(String.class));
		doReturn("Hello").when(rest).getForObject(eq("http://172.21.254.123:9090"), eq(String.class));
		doReturn("Hello").when(rest).getForObject(eq("http://172.21.254.123:9090/en"), eq(String.class));
		doReturn("Hola").when(rest).getForObject(eq("http://172.21.254.123:9090/es"), eq(String.class));
		doReturn("Hello").when(rest).getForObject(eq("http://k8s-greeting.default.svc.cluster.local:9090"), eq(String.class));
		doReturn("Hello").when(rest).getForObject(eq("http://k8s-greeting.default.svc.cluster.local:9090/en"), eq(String.class));
		doReturn("Hola").when(rest).getForObject(eq("http://k8s-greeting.default.svc.cluster.local:9090/es"), eq(String.class));
		doReturn("Hello").when(rest).getForObject(eq("https://thesgreeting2.azurewebsites.net"), eq(String.class));
		doReturn("Hello").when(rest).getForObject(eq("https://thesgreeting2.azurewebsites.net/en"), eq(String.class));
		doReturn("Hola").when(rest).getForObject(eq("https://thesgreeting2.azurewebsites.net/es"), eq(String.class));
		greetingService = new GreetingService(rest);
	}

	@AfterEach
	public void teardown() {
		greetingService = null;
	}

	@Test
	public void getGreeting() throws Exception {
		assertEquals("Hello", greetingService.getGreeting());
	}

	@Test
	public void getGreetingWithLocale() throws Exception {
		assertEquals("Hello", greetingService.getGreeting("en"));
		assertEquals("Hola", greetingService.getGreeting("es"));
	}

}
package com.example.demo;

import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

/**
 * @author Ryan Baxter
 */
public class NameServiceTest {

	@Test
	public void getNameTest() throws Exception {
		RestTemplate rest = mock(RestTemplate.class);
		doReturn("Ryan").when(rest).getForObject(eq("http://localhost:7070"), eq(String.class));
		doReturn("Ryan").when(rest).getForObject(eq("http://host.docker.internal:7070"), eq(String.class));
		doReturn("Ryan").when(rest).getForObject(eq("http://172.21.254.123:7070"), eq(String.class));
		doReturn("Ryan").when(rest).getForObject(eq("http://k8s-name.default.svc.cluster.local:7070"), eq(String.class));
		doReturn("Ryan").when(rest).getForObject(eq("https://thesname2.azurewebsites.net"), eq(String.class));
		NameService nameService = new NameService(rest);
		String name = nameService.getName();
		assertEquals(name, "Ryan");
	}

}
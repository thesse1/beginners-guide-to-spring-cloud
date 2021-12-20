package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"spring.cloud.config.enabled:false"})
public class WebApplicationTests {

	@Autowired
	TestRestTemplate rest;

	@MockBean
	RestTemplate restTemplate;

	@BeforeEach
	public void setup() {
		doReturn("Ryan").when(restTemplate).getForObject(eq("http://localhost:7070"), eq(String.class));
		doReturn("Hello").when(restTemplate).getForObject(eq("http://localhost:9090/en"), eq(String.class));
		doReturn("Ryan").when(restTemplate).getForObject(eq("http://host.docker.internal:7070"), eq(String.class));
		doReturn("Hello").when(restTemplate).getForObject(eq("http://host.docker.internal:9090/en"), eq(String.class));
		doReturn("Ryan").when(restTemplate).getForObject(eq("http://172.21.254.123:7070"), eq(String.class));
		doReturn("Ryan").when(restTemplate).getForObject(eq("http://k8s-name.default.svc.cluster.local:7070"), eq(String.class));
		doReturn("Hello").when(restTemplate).getForObject(eq("http://172.21.254.123:9090/en"), eq(String.class));
		doReturn("Hello").when(restTemplate).getForObject(eq("http://k8s-greeting.default.svc.cluster.local:9090/en"), eq(String.class));
		doReturn("Ryan").when(restTemplate).getForObject(eq("https://thesname2.azurewebsites.net"), eq(String.class));
		doReturn("Hello").when(restTemplate).getForObject(eq("https://thesgreeting2.azurewebsites.net/en"), eq(String.class));
	}

	@Test
	public void contextLoads() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept-Language", "en");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<String> greeting = rest.exchange("/", HttpMethod.GET, entity, String.class);
		assertEquals("Hello Ryan", greeting.getBody());
	}
}

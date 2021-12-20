package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Ryan Baxter
 */
@Service
public class NameService {
//	private static final String URL = "http://localhost:7070";
//	private static final String URL = "http://host.docker.internal:7070";
//	private static final String URL = "http://172.21.254.123:7070";
	private static final String URL = "http://k8s-name.default.svc.cluster.local:7070";
//	private static final String URL = "https://thesname2.azurewebsites.net";
	private RestTemplate rest;

	public NameService(RestTemplate rest) {
		this.rest = rest;
	}

	public String getName() {
		return rest.getForObject(URL, String.class);
	}
}

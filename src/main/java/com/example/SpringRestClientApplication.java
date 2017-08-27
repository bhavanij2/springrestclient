package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@SpringBootApplication
public class SpringRestClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestClientApplication.class, args);
	}

	@Bean
	public RestTemplate createRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) {
		return args -> {
			System.out.println("1 ************");
			ResponseEntity<Greeting> responseEntity = restTemplate.getForEntity("http://localhost:8080/greet", Greeting.class);
			System.out.println(responseEntity.getStatusCode());
			System.out.println(responseEntity.getBody());
			System.out.println(responseEntity.getBody().getContent());

			System.out.println("2 ***********");
			Greeting greeting = restTemplate.getForObject("http://localhost:8080/greet", Greeting.class);
			System.out.println(greeting);

			System.out.println("3 ************");
			Greeting greeting1 = new Greeting(2, "Posting data");
			ResponseEntity responseEntity1 = restTemplate.postForEntity("http://localhost:8080/post-greeting", greeting1, Greeting.class);
			System.out.println(responseEntity1);

			System.out.println("4 ************");
			Greeting greeting4 = new Greeting(2, "Posting data");
			Greeting response4 = restTemplate.postForObject("http://localhost:8080/post-greeting", greeting4, Greeting.class);
			System.out.println(response4);

			System.out.println("5 ************");
			Greeting greeting2 = new Greeting(2, "Posting data object");
			Greeting response = restTemplate.postForObject("http://localhost:8080/post-greeting-object", greeting2, Greeting.class);
			System.out.println(response);

			System.out.println("6 ************");
			ResponseEntity<Greeting> response6 = restTemplate.postForEntity("http://localhost:8080/post-greeting-object", greeting2, Greeting.class);
			System.out.println(response6);

			System.out.println("7 ************");
			Greeting greeting3 = new Greeting(2, "Posting data");
			RequestEntity<Greeting> requestEntity = new RequestEntity(greeting3, HttpMethod.POST, URI.create("http://localhost:8080/post-greeting-object"));
			ResponseEntity<Greeting> exchange = restTemplate.exchange(requestEntity, Greeting.class);
			System.out.println(exchange);

			System.out.println("8 ************");
			Greeting greeting8 = new Greeting(2, "Posting data object");
			Greeting response8 = restTemplate.postForObject("http://localhost:8080/post-greeting-no-return", greeting8, Greeting.class);
			System.out.println(response8);

			System.out.println("9  ************");
			ResponseEntity<Greeting> response9 = restTemplate.postForEntity("http://localhost:8080/post-greeting-no-return", greeting8, Greeting.class);
			System.out.println(response9);

			System.out.println("************");
		};
	}
}

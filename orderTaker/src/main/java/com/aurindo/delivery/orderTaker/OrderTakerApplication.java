package com.aurindo.delivery.orderTaker;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class OrderTakerApplication {

	private static final String QUEUE_NAME = "ordertaker";

	@Bean
	public Queue getQueue() {
		return new Queue(QUEUE_NAME);
	}

	public static void main(String[] args) {
		SpringApplication.run(OrderTakerApplication.class, args);
	}

}

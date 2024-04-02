package com.kafka.demo;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		Properties properties = new Properties();
		properties.put("acts", "1"); // 0 : not pending the response from the broker, 1 : pending the response from the broker , 2: pending the response from the leader
		properties.put("retries", "2"); // 0 : no retry, 1 : retry once, 2 : retry twice
		properties.put("bootstrap.servers", "localhost:9092");
		properties.put("batch.size", 16384);
		properties.put("linger.ms", 1000);
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		// confirm exactly once 
		// properties.put("idempotence", "true");
		// properties.put("retries", Integer.MAX_VALUE); // 0 : no retry, 1 : retry once, 2 : retry twice
		// properties.put("acts", "ALL");

		Producer<String, String> producer = new KafkaProducer<>(properties);
		for (int i = 0; i < 100; i++) {
			ProducerRecord<String, String> record = new ProducerRecord<>("mytopic", Integer.toString(i), Integer.toString(i));
			producer.send(record);
		}

		producer.close();
	}

}

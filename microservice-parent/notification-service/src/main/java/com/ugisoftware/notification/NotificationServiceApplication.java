package com.ugisoftware.notification;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.KafkaListener;

import com.ugisoftware.notification.event.OrderPlacedEvent;

import lombok.extern.slf4j.Slf4j;


@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class NotificationServiceApplication {


	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT +0:00"));
		SpringApplication.run(NotificationServiceApplication.class, args);
	}
	  @KafkaListener(topics = "notificationTopic")
	    public void handleNotification(OrderPlacedEvent orderPlacedEvent) {
	        // send out an email notification
	        log.info("Received Notification for Order - {}", orderPlacedEvent.getOrderNumber());
	    }
}


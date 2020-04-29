package com.learn2earn.todo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.learn2earn.todo.models.ToDo;

@Service
public class RabbitSenderService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("${harish.rabbitmq.exchange}")
	private String exchange;

	@Value("${harish.rabbitmq.emailRoutingkey}")
	private String emailRoutingkey;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public void sendForEmail(ToDo todo) {
		rabbitTemplate.convertAndSend(exchange, emailRoutingkey, todo);
		logger.info("Todo #: " +todo.getId()+ " sent for notification.");
	}

}

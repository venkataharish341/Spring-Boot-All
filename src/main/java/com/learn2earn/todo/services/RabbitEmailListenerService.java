package com.learn2earn.todo.services;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @author htavva
 * Listener class for listening to queue. Configuration defined in RabbitMQConfig class.
 */
public class RabbitEmailListenerService implements MessageListener {

	@Override
	public void onMessage(Message message) {
		System.out.println("Consuming in Email Listener : "+ new String(message.getBody()));	
	}

}

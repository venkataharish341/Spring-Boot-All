package com.learn2earn.todo.services;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author htavva
 * Listener class for listening to queue. Configuration defined in RabbitMQConfig class.
 */
@Service
public class RabbitEmailListenerService implements MessageListener {

	@Autowired
	private EmailService emailService;

	@Override
	public void onMessage(Message message) throws AmqpRejectAndDontRequeueException {
		String messageBody = new String(message.getBody());
		System.out.println("Consuming in Email Listener : "+ messageBody);

		try {
			emailService.sendMail("vharishtavva@gmail.com", "Good Email", messageBody);
		}catch(Exception ex) {
			// If we do not catch exception, the listener will be executed repeatedly. 
			// So, catching exception here and throwing a AmqpRejectAndDontRequeueException which will be handled as the name suggests.  
			throw new AmqpRejectAndDontRequeueException("Exception while sending Email.");
		}
	}

}

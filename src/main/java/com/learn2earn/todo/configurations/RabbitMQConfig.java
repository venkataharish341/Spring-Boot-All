package com.learn2earn.todo.configurations;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.learn2earn.todo.services.RabbitEmailListenerService;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

	@Value("${spring.rabbitmq.username}")
	private String userName;

	@Value("${spring.rabbitmq.password}")
	private String password;

	@Value("${spring.rabbitmq.host}")
	private String host;

	@Value("${harish.rabbitmq.emailQueue}")
	private String emailQueue;

	// Defining bean for Message Converter
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	// Configuring ConnectionFactory such that it creates a connection with RabbitMQ with specified credentials.
	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setAddresses(host);
		connectionFactory.setUsername(userName);
		connectionFactory.setPassword(password);
		return connectionFactory;
	}

	// Configuring RabbitTemplate(Just like RestTemplate) to use message converter.
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}

	/*From here Listner related.*/

	// A connection is set up with the AMQ topic/queue, it gets messages from that topic/queue and feeds them to your MessageListener.
	@Bean
	public MessageListenerContainer emailMessageListenerContainer() {
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
		simpleMessageListenerContainer.setConnectionFactory(this.connectionFactory());
		simpleMessageListenerContainer.setQueueNames(emailQueue);
		simpleMessageListenerContainer.setMessageListener(new RabbitEmailListenerService());
		return simpleMessageListenerContainer;
	}
}

/**
 * 
 */
package com.codewithabhishek.configuration;

import javax.jms.ConnectionFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

/*
 * @author codewithabhishek 
 * 
 *  Email codewithabhishek07@gmail.com
 *
 */
@Configuration
public class JmsConfiguration {

	@Bean
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		factory.setErrorHandler(new ErrorHandler() {
			@Override
			public void handleError(Throwable t) {
				System.err.println("An error has occurred in your Listener");
			}
		});
		return factory;
	}

	@Bean

	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	@Bean
	public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setConnectionFactory(connectionFactory);
		jmsTemplate.setMessageConverter(messageConverter);
		return jmsTemplate;
	}

}

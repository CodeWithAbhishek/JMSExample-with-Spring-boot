/**
 * 
 */
package com.codewithabhishek.impl;

import java.io.File;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

/**
 * @author codewithabhishek 
 * 
 *  Email codewithabhishek07@gmail.com
 *
 */

@Component
public class JmsImpl {

	@JmsListener(destination = "DistinationQueueName", containerFactory = "myFactory")
	public void recivingMsg(String msg) {

		System.out.println("=========================Message Rreceived============================");

		System.out.println(msg);

	}

}

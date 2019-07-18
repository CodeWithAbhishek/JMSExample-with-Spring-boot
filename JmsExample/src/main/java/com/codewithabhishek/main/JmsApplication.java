/**
 * 
 */
package com.codewithabhishek.main;

import java.io.File;

import javax.jms.Message;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author codewithabhishek
 * 
 *         Email codewithabhishek07@gmail.com
 *
 */

@SpringBootApplication
@RestController
@EnableJms
@ComponentScan(basePackages = "com.codewithabhishek")
public class JmsApplication {

	@Autowired
	private JmsTemplate Jtemp;

	String msg = "CodeWithAbhishek";// this is the meaasge which we are going to send

	@RequestMapping("/codewithabhishek")
	public String Sendmsg() {

		System.out.println("Message is sending");

		Jtemp.convertAndSend("DistinationQueueName", msg);// here we are sending the message to the Distination

		System.out.println("Message sent"); // for console

		return "Message sent";// for web browser
	}

	public static void main(String[] args) {

		SpringApplication.run(JmsApplication.class, args);
	}

}

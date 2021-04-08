package com.i9developement.disciplinams.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.i9developement.disciplinams.entities.Disciplina;

@Component
public class DisciplinaSendMessage {

	@Value("${disciplina.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${disciplina.rabbitmq.routingkey}")
	private String routingkey;
	
	
	public final RabbitTemplate rabbitTemplate;
	
	@Autowired
	public DisciplinaSendMessage(RabbitTemplate rabbitTemplate) {
		
		this.rabbitTemplate = rabbitTemplate;
	}
	
	
	public void sendMessage(Disciplina disciplina) {
		rabbitTemplate.convertAndSend(exchange, routingkey, disciplina);
	}
	
	
}

/*package com.i9developement.classroom.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.i9developement.classroom.data.vo.ClassroomVo;

@Component
public class ClassroomSendMessage {
	
	@Value("${classroom.rabbitmq.exchange}")
	String exchange;
	
	@Value("${classroom.rabbitmq.routingkey}")
	String routingkey;
	
	public final RabbitTemplate rabbitTemplate;
	

	@Autowired
	public ClassroomSendMessage(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public  void sendMessage(ClassroomVo classroomVo ) {
		rabbitTemplate.convertAndSend(exchange,routingkey,classroomVo);
	}
	
	
}
*/
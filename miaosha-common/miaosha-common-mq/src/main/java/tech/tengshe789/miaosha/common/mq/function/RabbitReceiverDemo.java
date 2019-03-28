package tech.tengshe789.miaosha.common.mq.function;

import com.rabbitmq.client.AMQP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @program: -miaosha
 * @author: tEngSHe789
 * @create: 2019-03-28 14:37
 **/
@Component
@Slf4j
public class RabbitReceiverDemo {

//	@RabbitListener(bindings = @QueueBinding(
//		value = @Queue(value = "queue-1",
//			durable="true"),
//		exchange = @Exchange(value = "exchange-1",
//			durable="true",
//			type= "topic",
//			ignoreDeclarationExceptions = "true"),
//		key = "springboot.*"
//	)
//	)
//	@RabbitHandler
//	public void onMessage(Message message, AMQP.Channel channel) throws Exception {
//		log.error("--------------------------------------");
//		log.error("消费端Payload: " + message.getPayload());
//		Long deliveryTag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
//	}
}

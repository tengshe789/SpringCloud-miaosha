package tech.tengshe789.miaosha.common.mq.function;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @program: -miaosha
 * @author: tEngSHe789
 * @create: 2019-03-28 14:20
 **/
@AllArgsConstructor
@Slf4j
@Service("rabbitSender")
public class RabbitSender {
	private final RabbitTemplate rabbitTemplate;

	/**
	 * 回调函数: confirm确认
	 */
	final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
		@Override
		public void confirm(CorrelationData correlationData, boolean ack, String cause) {
			log.error("correlationData: " + correlationData);
			log.error("ack: " + ack);
			if (!ack) {
				log.error("MQ异常处理....");
			}
		}
	};

	/**
	 * 回调函数: return返回
	 */
	final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
		@Override
		public void returnedMessage(org.springframework.amqp.core.Message message, int replyCode, String replyText,
									String exchange, String routingKey) {
			log.error("return exchange: " + exchange + ", routingKey: "
				+ routingKey + ", replyCode: " + replyCode + ", replyText: " + replyText);
		}
	};

	/**
	 * 简单发送
	 *
	 * @param routingKey
	 * @param message
	 */
	@SneakyThrows
	public void send(String routingKey, Object message) {
		String s = JSONUtil.toJsonStr(message);
		rabbitTemplate.setConfirmCallback(confirmCallback);
		rabbitTemplate.setReturnCallback(returnCallback);
		rabbitTemplate.convertAndSend(routingKey, s);
	}

	/**
	 * 发送消息方法调用: 构建Message消息
	 *
	 * @param routingKey
	 * @param message
	 * @param properties
	 * @throws Exception
	 */
	@SneakyThrows
	public void send(String routingKey, Object message, Map<String, Object> properties) {
		MessageHeaders mhs = new MessageHeaders(properties);
		Message msg = MessageBuilder.createMessage(message, mhs);
		rabbitTemplate.setConfirmCallback(confirmCallback);
		rabbitTemplate.setReturnCallback(returnCallback);
		//id + 时间戳 全局唯一
		CorrelationData correlationData = new CorrelationData(UUID.fastUUID().toString());
		rabbitTemplate.convertAndSend(routingKey, msg, correlationData);
	}

	/**
	 * 发送消息方法调用: 构建Message消息
	 *
	 * @param exchange
	 * @param routingKey
	 * @param message
	 * @param properties
	 * @throws Exception
	 */
	@SneakyThrows
	public void send(String exchange, String routingKey, Object message, Map<String, Object> properties) {
		MessageHeaders mhs = new MessageHeaders(properties);
		Message msg = MessageBuilder.createMessage(message, mhs);
		rabbitTemplate.setConfirmCallback(confirmCallback);
		rabbitTemplate.setReturnCallback(returnCallback);
		CorrelationData correlationData = new CorrelationData(UUID.fastUUID().toString());
		rabbitTemplate.convertAndSend(exchange, routingKey, msg, correlationData);
	}

	@Bean
	public String string() {
		return new String();
	}

}

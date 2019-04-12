package cn.tengshe789.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MQConfig {
	public static final String MIAOSHA_QUEUE = "miaosha.queue";
	public static final String QUEUE = "queue";
	public static final String TOPIC_QUEUE1 = "topic.queue1";
	public static final String TOPIC_QUEUE2 = "topic.queue2";
	public static final String HEADER_QUEUE = "header.queue";
	public static final String TOPIC_EXCHANGE = "topicExchage";
	public static final String FANOUT_EXCHANGE = "fanoutxchage";
	public static final String HEADERS_EXCHANGE = "headersExchage";

	/**
	 * Direct模式 交换机Exchange
	 * 先发送消息到交换机，做路由~
	 * 凡是子类及带有方法或属性的类都要加上注册Bean到Spring IoC的注解
	 */
	@Bean
	public Queue queue() {
		return new Queue(QUEUE, true);
	}

	/**
	 * Topic模式 交换机Exchange
	 * topic和queue的对应关系是一个topic拥有多个queue，当producer往broken发送消息时，
	 * 消息会存储在topic下的不同队列中，而一个队列只会被一个consumer消费，这样消息户被均衡负载到不同的队列下，
	 * 也就是会被多个消费者并行消费，顺序就无法保证了。
	 * 该怎么办呢？答案是把需要顺序消费的消息发送到同一台broker server下的同一个队列，
	 * 而这些消息也只会被同一个消费者消费，这样就可以保证严格的顺序了
	 */
	@Bean
	public Queue topicQueue1() {
		return new Queue(TOPIC_QUEUE1, true);
	}

	@Bean
	public Queue topicQueue2() {
		return new Queue(TOPIC_QUEUE2, true);
	}

	@Bean
	public TopicExchange topicExchage() {
		return new TopicExchange(TOPIC_EXCHANGE);
	}

	@Bean
	public Binding topicBinding1() {
		return BindingBuilder.bind(topicQueue1()).to(topicExchage()).with("topic.key1");
	}

	@Bean
	public Binding topicBinding2() {
		return BindingBuilder.bind(topicQueue2()).to(topicExchage()).with("topic.#");
	}

	/**
	 * Fanout模式（广播模式） 交换机Exchange
	 * 任何发送到Fanout Exchange的消息都会被转发到与该Exchange绑定(Binding)的所有Queue上
	 * 1.可以理解为路由表的模式
	 * 2.这种模式不需要RouteKey
	 * 3.这种模式需要提前将Exchange与Queue进行绑定，一个Exchange可以绑定多个Queue，一个Queue可以同多个Exchange进行绑定。
	 * 4.如果接受到消息的Exchange没有与任何Queue绑定，则消息会被抛弃。
	 */
	@Bean
	public FanoutExchange fanoutExchage() {
		return new FanoutExchange(FANOUT_EXCHANGE);
	}

	@Bean
	public Binding FanoutBinding1() {
		return BindingBuilder.bind(topicQueue1()).to(fanoutExchage());
	}

	@Bean
	public Binding FanoutBinding2() {
		return BindingBuilder.bind(topicQueue2()).to(fanoutExchage());
	}

	/**
	 * Header模式 交换机Exchange
	 */
	@Bean
	public HeadersExchange headersExchage() {
		return new HeadersExchange(HEADERS_EXCHANGE);
	}

	@Bean
	public Queue headerQueue1() {
		return new Queue(HEADER_QUEUE, true);
	}

	@Bean
	public Binding headerBinding() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("header1", "value1");
		map.put("header2", "value2");
		return BindingBuilder.bind(headerQueue1()).to(headersExchage()).whereAll(map).match();
	}

}

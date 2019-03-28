package tech.tengshe789.miaosha.common.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.tengshe789.miaosha.common.core.constants.RabbitInfo;
import tech.tengshe789.miaosha.common.mq.entity.RabbitProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: -miaosha
 * @author: tEngSHe789
 * @create: 2019-03-28 13:49
 **/
@Configuration
@EnableRabbit
@EnableConfigurationProperties(RabbitProperties.class)
public class RabbitmqAutoConfiguration {

	/**
	 * 配置RabbitAdmin来管理rabbit.
	 * rabbitAdmin组件是一个管理组件，
	 * 主要是用户通过该组件进行rabbitmq的队列交换器虚拟主机等等进行操作
	 *
	 * @param connectionFactory
	 * @return
	 */
	@Bean
	public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
		RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
		//用RabbitAdmin一定要配置这个，spring加载的是后就会加载这个类================特别重要
		rabbitAdmin.setAutoStartup(true);
		return rabbitAdmin;
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		return new RabbitTemplate(connectionFactory);
	}

	/**
	 * Direct模式 交换机Exchange
	 * 先发送消息到交换机，做路由~
	 * 凡是子类及带有方法或属性的类都要加上注册Bean到Spring IoC的注解
	 */
	@Bean
	public Queue queue() {
		return new Queue(RabbitInfo.QUEUE, true);
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
		return new Queue(RabbitInfo.TOPIC_QUEUE1, true);
	}

	@Bean
	public Queue topicQueue2() {
		return new Queue(RabbitInfo.TOPIC_QUEUE2, true);
	}

	@Bean
	public TopicExchange topicExchage() {
		return new TopicExchange(RabbitInfo.TOPIC_EXCHANGE);
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
		return new FanoutExchange(RabbitInfo.FANOUT_EXCHANGE);
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
		return new HeadersExchange(RabbitInfo.HEADERS_EXCHANGE);
	}

	@Bean
	public Queue headerQueue1() {
		return new Queue(RabbitInfo.HEADER_QUEUE, true);
	}

	@Bean
	public Binding headerBinding() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("header1", "value1");
		map.put("header2", "value2");
		return BindingBuilder.bind(headerQueue1()).to(headersExchage()).whereAll(map).match();
	}
}

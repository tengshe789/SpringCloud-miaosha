package tech.tengshe789.miaosha.common.mq.annotation;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Import;
import tech.tengshe789.miaosha.common.mq.config.RabbitmqAutoConfiguration;

import java.lang.annotation.*;

/**
 * @program: -miaosha
 * @description: 开启 rabbitmq
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-03-28
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(RabbitmqAutoConfiguration.class)
public @interface EnableMiaoshaRabbitmq {
}

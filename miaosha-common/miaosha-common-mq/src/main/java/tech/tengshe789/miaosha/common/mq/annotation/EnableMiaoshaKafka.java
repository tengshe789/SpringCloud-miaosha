package tech.tengshe789.miaosha.common.mq.annotation;

import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;
import tech.tengshe789.miaosha.common.mq.config.KafkaProducerConfig;

import java.lang.annotation.*;

/**
 * @program: -miaosha
 * @author: tEngSHe789
 * @create: 2019-03-28 14:15
 **/
@EnableKafka
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(KafkaProducerConfig.class)
public @interface EnableMiaoshaKafka {
}

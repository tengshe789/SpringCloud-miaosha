package cn.tengshe789.controller;


import javax.servlet.http.HttpSession;

import cn.tengshe789.rabbitmq.MQReceiver;
import cn.tengshe789.rabbitmq.MQSender;
import cn.tengshe789.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/demo")
public class SampleController {
    @Autowired
    MQReceiver receiver;

    @Autowired
    MQSender sender;

//        /**
//         * 发送测试消息队列
//         */
//        @RequestMapping(value = "/addRabbitMq", method = RequestMethod.GET)
//        public @ResponseBody Result addEntity(HttpSession httpSession) {
//            sender.send("jkljklkjljjkljl");
//            return Result.success()
//        }
    }

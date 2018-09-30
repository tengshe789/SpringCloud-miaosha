package cn.tengshe789.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: miaoSha
 * @description: Hello Docker
 * @author: tEngSHe789
 * @create: 2018-09-20 16:45
 **/
@RestController
public class DockerController {

    @RequestMapping("/")
    public String index() {
        return "Hello Docker!";
    }
}

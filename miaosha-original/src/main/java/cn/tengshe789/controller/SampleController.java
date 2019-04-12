package cn.tengshe789.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class SampleController {

	@RequestMapping("/hello")
	public String index() {
		return "Hello World";
	}

}

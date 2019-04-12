package tech.tengshe789.miaosha.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

/**
 * @program: -miaosha
 * @author: tEngSHe789
 * @create: 2019-03-31 17:11
 **/
@Controller
@CrossOrigin
public class LoginController {

	@GetMapping("/to_login")
	public Mono<String> login(Model model) {
		return Mono.just("login");
	}
}

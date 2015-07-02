package cn.duozhilin.restaurants.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldSpringMVC {
	
	@RequestMapping("/helloWorld.action")
	public String hello(Model model){
		model.addAttribute("message", "Hello World!");
		return "hello world";
	}

}

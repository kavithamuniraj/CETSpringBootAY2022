package com.sbstransit.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
	@GetMapping("/greet")
	public String greeting() {
		return "greeting";
	}

}

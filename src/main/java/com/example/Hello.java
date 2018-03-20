package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/hello")
public class Hello {
	
	@RequestMapping(value="/getString")
	public String getString() {
		return "hello springboot , I'm coming";
	}
}

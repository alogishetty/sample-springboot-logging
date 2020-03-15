package com.sample.web;

import com.sample.service.HelloWorldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	private static Log logger = LogFactory.getLog(SampleController.class);

	@Autowired
	private HelloWorldService helloWorldService;

	@GetMapping("/")
	@ResponseBody
	public String helloWorld() {
		logger.trace("Hello World!");
		logger.debug("How are you today?");
		logger.info("I am fine.");
		logger.warn("I love programming.");
		logger.error("I am programming.");
		return this.helloWorldService.getHelloMessage();
	}

	@GetMapping("/unhandled")
	@ResponseBody
	public String unhandled() {
		return this.helloWorldService.getUnhandledException();
	}

	@GetMapping("/handled")
	@ResponseBody
	public String handled() {
		return this.helloWorldService.getHandledException();
	}

	@GetMapping("/nested")
	@ResponseBody
	public String nested() {
		return this.helloWorldService.getNestedLogging();
	}

	@GetMapping("/error-testing")
	@ResponseBody
	public String errorTesting() {
		return this.helloWorldService.getErrorTestingLogging();
	}

}

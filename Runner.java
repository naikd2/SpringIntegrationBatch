package com.dhruvit;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import javax.annotation.PostConstruct;

//@Component
public class Runner implements ApplicationRunner {


	@PostConstruct
	public void init() {
		System.out.println("Autowiring");
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// Spring Integration Application...



		System.out.println("Starting Application");
	}

}

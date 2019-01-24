package com.sweet.apple;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.logging.LoggingApplicationListener;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;

import java.util.Iterator;
import java.util.Set;

@SpringBootApplication
@ComponentScan(basePackages={"com.sweet.apple"})

public class PoisonousApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(PoisonousApplication.class);
		Set<ApplicationListener<?>> listeners = builder.application().getListeners();
		for (Iterator<ApplicationListener<?>> it = listeners.iterator(); it.hasNext();) {
			ApplicationListener<?> listener = it.next();
			if (listener instanceof LoggingApplicationListener) {
				it.remove();
			}
		}
		builder.application().setListeners(listeners);
		builder.run(args);
	}
}

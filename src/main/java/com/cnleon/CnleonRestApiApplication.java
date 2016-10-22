package com.cnleon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class CnleonRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CnleonRestApiApplication.class, args);
	}
}

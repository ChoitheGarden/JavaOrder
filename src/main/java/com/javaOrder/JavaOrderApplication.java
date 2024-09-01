package com.javaOrder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD

@SpringBootApplication
=======
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableJpaAuditing
>>>>>>> order_ver2
public class JavaOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaOrderApplication.class, args);
	}

}

package br.com.ltst.springwebmvcbasicodigitalinnovationone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringWebMvcBasicoDigitalInnovationOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebMvcBasicoDigitalInnovationOneApplication.class, args);
	}

}

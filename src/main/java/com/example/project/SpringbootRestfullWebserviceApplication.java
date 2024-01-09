package com.example.project;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "SpringBoot REST API Documentation",description = "SpringBoot REST API Documentation",version = "v1.0 ",
				contact =@Contact(name = "Raja ",email = "raja90r@out.look.com",url = "rajaramadoss.blogspot.com "),
				license =@License(name = "Apache 2.o",url = "rajaramadoss.blogspot.com")),
		externalDocs = @ExternalDocumentation(description = "Spring Boot user management Documentation ",url = "https://rajaramadoss.blogspot.com/user_management.html")
)
public class SpringbootRestfullWebserviceApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfullWebserviceApplication.class, args);
	}

}

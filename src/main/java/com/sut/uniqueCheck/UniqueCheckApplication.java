package com.sut.uniqueCheck;

import com.sut.uniqueCheck.service.DataService;
import com.sut.uniqueCheck.service.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UniqueCheckApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Autowired
	private DataService dataService;

	@Bean
	public DataService getDataService(@Value("${app.name}") String dataService) {
		return new DataServiceImpl();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(UniqueCheckApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(UniqueCheckApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}


package io.srhojo.java.loggerreader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {SwaggerConfig.class})
public class LoggerReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoggerReaderApplication.class, args);
	}

}

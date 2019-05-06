package gob.pe.essalud.appincapacidad;

import gob.pe.essalud.appincapacidad.incapacidad.util.FileStorageProperties;
import gob.pe.essalud.appincapacidad.incapacidad.util.WebServiceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class, WebServiceProperties.class
})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}
}

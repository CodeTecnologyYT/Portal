package gob.pe.essalud.appincapacidad.incapacidad.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;
}

package essalud.gob.pe.portal.incapacidad.incapacidad.util;

import essalud.gob.pe.portal.incapacidad.incapacidad.model.Archivo;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileSystemStorageService {

	private Path uploadLocation;
	
	@PostConstruct
	public void init() {
		this.uploadLocation = Paths.get(Constants.UPLOAD_LOCATION+(new SimpleDateFormat("MM-dd-yyyy").format(new Date())));
		try {
			Files.createDirectories(uploadLocation);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage", e);
		}
	}

	public Archivo store(MultipartFile file) throws IOException{
		Archivo archivo= new Archivo();
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		System.out.println("File: "+filename);
		archivo.setNombre(filename);
		try (InputStream inputStream = file.getInputStream()) {
			Files.copy(inputStream, this.uploadLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
		}
		String url=uploadLocation.resolve(filename).normalize().toString();
		archivo.setUrlAcceso(url);

		return archivo;
	}
	
	public Path getUploadLocation() {
		return uploadLocation;
	}
}


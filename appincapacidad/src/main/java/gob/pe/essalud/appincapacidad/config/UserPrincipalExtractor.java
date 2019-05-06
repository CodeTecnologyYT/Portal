package gob.pe.essalud.appincapacidad.config;

import gob.pe.essalud.appincapacidad.seguridad.model.UsuarioLogueado;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

import javax.servlet.http.HttpSession;
import java.util.Map;


public class UserPrincipalExtractor implements PrincipalExtractor {
    private static final Logger logger = LogManager.getLogger(UserPrincipalExtractor.class);
      @Autowired
      HttpSession session;

      public Object extractPrincipal(Map<String, Object> map){

            session.setAttribute("usuarioLogeado", UsuarioLogueado.builder()
                    .id(Long.parseLong(String.valueOf(map.get("id"))))
                    .tipoDocumento("06")
                    .siglaDocumento((String)map.get("siglaDocumento"))
                    .apellidoMaterno((String)map.get("apellidoMaterno"))
                    .numeroDocumento((String)map.get("numeroDocumento"))
                    .rol((String)map.get("rol"))
//                    .rucEmpleador((String)map.get("rucEmpleador"))
                    .rucEmpleador("20410016070")
                    .codigoRol((String)map.get("codigoRol"))
                    .correo((String)map.get("correo"))
                    .build());
            return new UsuarioLogueado();
    }
}

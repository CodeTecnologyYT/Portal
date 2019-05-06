package gob.pe.essalud.appincapacidad.comunes.rest;

import gob.pe.essalud.appincapacidad.incapacidad.model.Asegurado;
import gob.pe.essalud.appincapacidad.incapacidad.model.Resultado;
import gob.pe.essalud.appincapacidad.comunes.service.ComunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comun")
public class ComunRest {

    @Autowired
    ComunService comunService;

    @PostMapping("/datosAsegurado")
    private ResponseEntity <Resultado>datosDelAsegurado(@RequestBody Asegurado asegurado){
        String nombreCompleto=comunService.getDatosAsegurado(asegurado);
        asegurado.setNombres(nombreCompleto);
        Resultado resultado= new Resultado();
        resultado.setFlag(1);
        resultado.setObject(asegurado);
        return new ResponseEntity<Resultado>(resultado, HttpStatus.OK);
    }
}

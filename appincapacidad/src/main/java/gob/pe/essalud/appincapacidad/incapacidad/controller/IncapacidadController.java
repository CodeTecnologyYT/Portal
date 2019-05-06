package gob.pe.essalud.appincapacidad.incapacidad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/incapacidad")
public class IncapacidadController {

    @GetMapping(value="/")
    public String inicioIncapacidad(){
        return "incapacidad/incapacidad";
    }

    @GetMapping(value="/detalleIncapacidad")
    public String detalleSolicitud(){
        return "incapacidad/detalleIncapacidad";
    }
    @GetMapping(value="/detalleSolicitudes")
    public String incapacidadSolicitud(){
        return "incapacidad/incapacidadSolicitados";
    }
}

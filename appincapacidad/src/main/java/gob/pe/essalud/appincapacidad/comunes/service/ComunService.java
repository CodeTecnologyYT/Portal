package gob.pe.essalud.appincapacidad.comunes.service;

import gob.pe.essalud.appincapacidad.incapacidad.model.Asegurado;

public interface ComunService {

    public String     getRazonSocial(String ruc);
    public String  getDatosAsegurado(Asegurado asegurado);
}

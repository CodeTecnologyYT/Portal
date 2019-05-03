package essalud.gob.pe.portal.incapacidad.comunes.service;

import essalud.gob.pe.portal.incapacidad.incapacidad.model.Asegurado;

public interface ComunService {

    public String     getRazonSocial(String ruc);
    public String  getDatosAsegurado(Asegurado asegurado);
}

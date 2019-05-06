package gob.pe.essalud.appincapacidad.incapacidad.service;

import gob.pe.essalud.appincapacidad.seguridad.model.UsuarioLogueado;
import gob.pe.essalud.appincapacidad.incapacidad.model.Asegurado;
import gob.pe.essalud.appincapacidad.incapacidad.model.Resultado;
import gob.pe.essalud.appincapacidad.incapacidad.model.SolicitudIncapacidad;

public interface IncapacidadService {

    public Resultado listaCitt(Asegurado asegurado, String ruc);
    public Resultado tiene20PrimerosDias(SolicitudIncapacidad solicitud);
    public Resultado validarSolicitud(SolicitudIncapacidad solicitud);
    public Resultado registrarSolicitud(SolicitudIncapacidad solicitud);
    public Resultado getListaIncapacidadSolicitado(UsuarioLogueado usuario, String tipo);
    public Resultado deleteSolicitudIncapacidad(String idSolicitud,UsuarioLogueado usuario);
    public Resultado enviarSolicitudIncapacidad(UsuarioLogueado usuario,String tipo);

}

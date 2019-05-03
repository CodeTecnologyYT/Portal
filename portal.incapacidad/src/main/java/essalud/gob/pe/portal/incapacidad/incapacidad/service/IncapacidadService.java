package essalud.gob.pe.portal.incapacidad.incapacidad.service;

import essalud.gob.pe.portal.incapacidad.incapacidad.model.*;

public interface IncapacidadService {

    public Resultado listaCitt(Asegurado asegurado, String ruc);
    public Resultado tiene20PrimerosDias(SolicitudIncapacidad solicitud);
    public Resultado validarSolicitud(SolicitudIncapacidad solicitud);
    public Resultado registrarSolicitud(SolicitudIncapacidad solicitud);
    public Resultado getListaIncapacidadSolicitado(UsuarioBean usuario,String tipo);
    public Resultado deleteSolicitudIncapacidad(String idSolicitud,UsuarioBean usuario);
    public Resultado enviarSolicitudIncapacidad(UsuarioBean usuario,String tipo);

}

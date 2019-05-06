package gob.pe.essalud.appincapacidad.incapacidad.service;


import gob.pe.essalud.appincapacidad.seguridad.model.UsuarioLogueado;
import gob.pe.essalud.appincapacidad.incapacidad.model.Asegurado;
import gob.pe.essalud.appincapacidad.incapacidad.model.Citt;
import gob.pe.essalud.appincapacidad.incapacidad.model.Solicitud;
import gob.pe.essalud.appincapacidad.incapacidad.model.SolicitudIncapacidad;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface GlobalService {
    public Integer EvualarVeintePrimerosDias(SolicitudIncapacidad solicitud);

    public Map<String, Object> tieneVeinte(SolicitudIncapacidad solicitud);

    public boolean verificarFinTramite(Date fechaFin);

    public Map esPagoReembolso(SolicitudIncapacidad solicitud) throws MalformedURLException;

    public Boolean insertIncapacidad(SolicitudIncapacidad solicitud) throws MalformedURLException;

    public List<Citt> listaCitt(Asegurado asegurado, String ruc) throws MalformedURLException;

    public List<Solicitud> getListaIncapacidad(UsuarioLogueado usuario, String tipoSolicitud, String tipoEstado)throws MalformedURLException;

    public boolean deleteSolicitud(String idSolicitud, UsuarioLogueado usuario) throws MalformedURLException;

    public boolean enviarSolicitud(UsuarioLogueado usuario) throws MalformedURLException;
}
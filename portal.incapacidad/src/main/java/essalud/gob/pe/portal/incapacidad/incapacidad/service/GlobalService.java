package essalud.gob.pe.portal.incapacidad.incapacidad.service;


import essalud.gob.pe.portal.incapacidad.incapacidad.model.*;
import org.springframework.web.bind.annotation.RequestBody;

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

    public List<Solicitud> getListaIncapacidad(UsuarioBean usuario,String tipoSolicitud,String tipoEstado)throws MalformedURLException;

    public boolean deleteSolicitud(String idSolicitud, UsuarioBean usuario) throws MalformedURLException;

    public boolean enviarSolicitud(UsuarioBean usuario) throws MalformedURLException;
}
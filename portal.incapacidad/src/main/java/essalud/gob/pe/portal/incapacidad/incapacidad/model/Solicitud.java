package essalud.gob.pe.portal.incapacidad.incapacidad.model;

import lombok.Data;

import java.util.Date;

public @Data class Solicitud {
    private Integer codigoSolicitud;
    private String tipoDocumneto;
    private String numeroDocumento;
    private String nombre;
    private String razonSocial;
    private String ruc;
    private String citt;
    private Date fechaInicioSolicitud;
    private Date fechaFinSolicitud;
    private String fechaCese;
    private String monto;
    private Date fechaInicioVacaciones;
    private Date fechaFinVacaciones;
}

package gob.pe.essalud.appincapacidad.incapacidad.model;

import lombok.Data;

import java.util.Date;

public @Data class PeriodoSolicitud {
    private Date fechaInicioSolicitud;
    private Date fechaFinSolicitud;
    private Long cantidadDiasSolicitados;
    private Integer tiene20Dias;
}

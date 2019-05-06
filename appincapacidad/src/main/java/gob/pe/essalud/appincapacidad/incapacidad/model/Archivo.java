package gob.pe.essalud.appincapacidad.incapacidad.model;

import lombok.Data;

public @Data class Archivo {
    private Integer idArchivo;
    private Integer idSolicitud;
    private String  nombre;
    private String  urlAcceso;
    private String  tipo;
}

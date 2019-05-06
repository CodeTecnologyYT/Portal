package gob.pe.essalud.appincapacidad.incapacidad.model;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Solicitud {
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

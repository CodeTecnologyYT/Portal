package essalud.gob.pe.portal.incapacidad.incapacidad.model;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public @Data class SolicitudIncapacidad implements Serializable {

    private static final long serialVersionUID = 42L;
    private Integer idSolicitud;
    private UsuarioBean user;
    private String tp_sub;
    private Asegurado asegurado;
    private EntidadEmpleadora entidad;
    private List<Citt> listcitt;
    private List<Citt> listprim20d;
    private Date fecini_sol;
    private Date fecfin_sol;
    private Date fecinitemporal;
    private Date fecfintemporal;
    private Integer esSectorPrivado;
    private Date fecini_vac;
    private Date fecfin_vac;
    private Integer estaTrabajando;
    private Date fec_cese;
    private Double monto_sol;
    private Integer tieneSctr;
    private Archivo archivos;

}

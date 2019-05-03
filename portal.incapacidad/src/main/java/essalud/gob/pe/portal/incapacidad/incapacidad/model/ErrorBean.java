package essalud.gob.pe.portal.incapacidad.incapacidad.model;

import lombok.Data;

public @Data class ErrorBean {
    private String codigo;
    private String descripcion;

    public ErrorBean(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }
}

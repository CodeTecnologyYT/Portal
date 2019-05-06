package gob.pe.essalud.appincapacidad.incapacidad.model;

import lombok.Data;

public @Data class Resultado {
    private Integer flag;//correcto o incorrecto
    private Integer tipoResultado;
    private String  descripcion;
    private Object  object;
}

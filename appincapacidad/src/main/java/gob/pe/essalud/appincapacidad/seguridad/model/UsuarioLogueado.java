package gob.pe.essalud.appincapacidad.seguridad.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLogueado {
    private Long id;
    private String tipoDocumento;
    private String siglaDocumento;
    private String numeroDocumento;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String rucEmpleador;
    private String rol;
    private String codigoRol;
    private String apellidosNombres;
    private String menuHtml;


}

package essalud.gob.pe.portal.incapacidad.incapacidad.model.app;

public class UsuarioLogueado {
    private Long id;
    private String tipoDocumento;
    private String siglaDocumento;
    private String numeroDocumento;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String rucEmpleador;
    private String rol;
    private String codigoRol;

    public UsuarioLogueado() {
    }


    public Long getId() {
        return this.id;
    }

    public String getTipoDocumento() {
        return this.tipoDocumento;
    }

    public String getSiglaDocumento() {
        return this.siglaDocumento;
    }

    public String getNumeroDocumento() {
        return this.numeroDocumento;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellidoPaterno() {
        return this.apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return this.apellidoMaterno;
    }

    public String getCorreo() {
        return this.correo;
    }

    public String getRucEmpleador() {
        return this.rucEmpleador;
    }

    public String getRol() {
        return this.rol;
    }

    public String getCodigoRol() {
        return this.codigoRol;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public void setSiglaDocumento(String siglaDocumento) {
        this.siglaDocumento = siglaDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setRucEmpleador(String rucEmpleador) {
        this.rucEmpleador = rucEmpleador;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setCodigoRol(String codigoRol) {
        this.codigoRol = codigoRol;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UsuarioLogueado)) return false;
        final UsuarioLogueado other = (UsuarioLogueado) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$tipoDocumento = this.getTipoDocumento();
        final Object other$tipoDocumento = other.getTipoDocumento();
        if (this$tipoDocumento == null ? other$tipoDocumento != null : !this$tipoDocumento.equals(other$tipoDocumento))
            return false;
        final Object this$siglaDocumento = this.getSiglaDocumento();
        final Object other$siglaDocumento = other.getSiglaDocumento();
        if (this$siglaDocumento == null ? other$siglaDocumento != null : !this$siglaDocumento.equals(other$siglaDocumento))
            return false;
        final Object this$numeroDocumento = this.getNumeroDocumento();
        final Object other$numeroDocumento = other.getNumeroDocumento();
        if (this$numeroDocumento == null ? other$numeroDocumento != null : !this$numeroDocumento.equals(other$numeroDocumento))
            return false;
        final Object this$nombre = this.getNombre();
        final Object other$nombre = other.getNombre();
        if (this$nombre == null ? other$nombre != null : !this$nombre.equals(other$nombre)) return false;
        final Object this$apellidoPaterno = this.getApellidoPaterno();
        final Object other$apellidoPaterno = other.getApellidoPaterno();
        if (this$apellidoPaterno == null ? other$apellidoPaterno != null : !this$apellidoPaterno.equals(other$apellidoPaterno))
            return false;
        final Object this$apellidoMaterno = this.getApellidoMaterno();
        final Object other$apellidoMaterno = other.getApellidoMaterno();
        if (this$apellidoMaterno == null ? other$apellidoMaterno != null : !this$apellidoMaterno.equals(other$apellidoMaterno))
            return false;
        final Object this$correo = this.getCorreo();
        final Object other$correo = other.getCorreo();
        if (this$correo == null ? other$correo != null : !this$correo.equals(other$correo)) return false;
        final Object this$rucEmpleador = this.getRucEmpleador();
        final Object other$rucEmpleador = other.getRucEmpleador();
        if (this$rucEmpleador == null ? other$rucEmpleador != null : !this$rucEmpleador.equals(other$rucEmpleador))
            return false;
        final Object this$rol = this.getRol();
        final Object other$rol = other.getRol();
        if (this$rol == null ? other$rol != null : !this$rol.equals(other$rol)) return false;
        final Object this$codigoRol = this.getCodigoRol();
        final Object other$codigoRol = other.getCodigoRol();
        if (this$codigoRol == null ? other$codigoRol != null : !this$codigoRol.equals(other$codigoRol)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UsuarioLogueado;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $tipoDocumento = this.getTipoDocumento();
        result = result * PRIME + ($tipoDocumento == null ? 43 : $tipoDocumento.hashCode());
        final Object $siglaDocumento = this.getSiglaDocumento();
        result = result * PRIME + ($siglaDocumento == null ? 43 : $siglaDocumento.hashCode());
        final Object $numeroDocumento = this.getNumeroDocumento();
        result = result * PRIME + ($numeroDocumento == null ? 43 : $numeroDocumento.hashCode());
        final Object $nombre = this.getNombre();
        result = result * PRIME + ($nombre == null ? 43 : $nombre.hashCode());
        final Object $apellidoPaterno = this.getApellidoPaterno();
        result = result * PRIME + ($apellidoPaterno == null ? 43 : $apellidoPaterno.hashCode());
        final Object $apellidoMaterno = this.getApellidoMaterno();
        result = result * PRIME + ($apellidoMaterno == null ? 43 : $apellidoMaterno.hashCode());
        final Object $correo = this.getCorreo();
        result = result * PRIME + ($correo == null ? 43 : $correo.hashCode());
        final Object $rucEmpleador = this.getRucEmpleador();
        result = result * PRIME + ($rucEmpleador == null ? 43 : $rucEmpleador.hashCode());
        final Object $rol = this.getRol();
        result = result * PRIME + ($rol == null ? 43 : $rol.hashCode());
        final Object $codigoRol = this.getCodigoRol();
        result = result * PRIME + ($codigoRol == null ? 43 : $codigoRol.hashCode());
        return result;
    }

    public String toString() {
        return "UsuarioLogueado(id=" + this.getId() + ", tipoDocumento=" + this.getTipoDocumento() + ", siglaDocumento=" + this.getSiglaDocumento() + ", numeroDocumento=" + this.getNumeroDocumento() + ", nombre=" + this.getNombre() + ", apellidoPaterno=" + this.getApellidoPaterno() + ", apellidoMaterno=" + this.getApellidoMaterno() + ", correo=" + this.getCorreo() + ", rucEmpleador=" + this.getRucEmpleador() + ", rol=" + this.getRol() + ", codigoRol=" + this.getCodigoRol() + ")";
    }
}

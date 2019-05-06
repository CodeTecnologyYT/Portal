
package gob.pe.essalud.appincapacidad.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para maternityBean complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="maternityBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codEDocBenef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codETipoSub" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codTDocBenef" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="codTTipoSub" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="codUsuarioCrea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecCese" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecFinCitt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecFinSubSolic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecIniCitt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecIniSubSolic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecUsuarioCrea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flgActivoInactivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flgSectorEducacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ipUsuarioCrea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreCompleto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numArmada" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numCitt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numDeclJurada" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numDiasCitt" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numDiasSub" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numDocBenef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numMontoPagad" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="numMontoSolic" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="numRUCEmple" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="razonSocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoEmpleado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoSeguro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="zonaUpe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "maternityBean", propOrder = {
    "codEDocBenef",
    "codETipoSub",
    "codTDocBenef",
    "codTTipoSub",
    "codUsuarioCrea",
    "fecCese",
    "fecFinCitt",
    "fecFinSubSolic",
    "fecIniCitt",
    "fecIniSubSolic",
    "fecUsuarioCrea",
    "flgActivoInactivo",
    "flgSectorEducacion",
    "ipUsuarioCrea",
    "nombreCompleto",
    "numArmada",
    "numCitt",
    "numDeclJurada",
    "numDiasCitt",
    "numDiasSub",
    "numDocBenef",
    "numMontoPagad",
    "numMontoSolic",
    "numRUCEmple",
    "razonSocial",
    "tipoEmpleado",
    "tipoSeguro",
    "zonaUpe"
})
public class MaternityBean {

    protected String codEDocBenef;
    protected String codETipoSub;
    protected int codTDocBenef;
    protected int codTTipoSub;
    protected String codUsuarioCrea;
    protected String fecCese;
    protected String fecFinCitt;
    protected String fecFinSubSolic;
    protected String fecIniCitt;
    protected String fecIniSubSolic;
    protected String fecUsuarioCrea;
    protected String flgActivoInactivo;
    protected String flgSectorEducacion;
    protected String ipUsuarioCrea;
    protected String nombreCompleto;
    protected int numArmada;
    protected String numCitt;
    protected int numDeclJurada;
    protected int numDiasCitt;
    protected int numDiasSub;
    protected String numDocBenef;
    protected double numMontoPagad;
    protected double numMontoSolic;
    protected String numRUCEmple;
    protected String razonSocial;
    protected String tipoEmpleado;
    protected String tipoSeguro;
    protected String zonaUpe;

    /**
     * Obtiene el valor de la propiedad codEDocBenef.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodEDocBenef() {
        return codEDocBenef;
    }

    /**
     * Define el valor de la propiedad codEDocBenef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodEDocBenef(String value) {
        this.codEDocBenef = value;
    }

    /**
     * Obtiene el valor de la propiedad codETipoSub.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodETipoSub() {
        return codETipoSub;
    }

    /**
     * Define el valor de la propiedad codETipoSub.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodETipoSub(String value) {
        this.codETipoSub = value;
    }

    /**
     * Obtiene el valor de la propiedad codTDocBenef.
     * 
     */
    public int getCodTDocBenef() {
        return codTDocBenef;
    }

    /**
     * Define el valor de la propiedad codTDocBenef.
     * 
     */
    public void setCodTDocBenef(int value) {
        this.codTDocBenef = value;
    }

    /**
     * Obtiene el valor de la propiedad codTTipoSub.
     * 
     */
    public int getCodTTipoSub() {
        return codTTipoSub;
    }

    /**
     * Define el valor de la propiedad codTTipoSub.
     * 
     */
    public void setCodTTipoSub(int value) {
        this.codTTipoSub = value;
    }

    /**
     * Obtiene el valor de la propiedad codUsuarioCrea.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodUsuarioCrea() {
        return codUsuarioCrea;
    }

    /**
     * Define el valor de la propiedad codUsuarioCrea.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodUsuarioCrea(String value) {
        this.codUsuarioCrea = value;
    }

    /**
     * Obtiene el valor de la propiedad fecCese.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecCese() {
        return fecCese;
    }

    /**
     * Define el valor de la propiedad fecCese.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecCese(String value) {
        this.fecCese = value;
    }

    /**
     * Obtiene el valor de la propiedad fecFinCitt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecFinCitt() {
        return fecFinCitt;
    }

    /**
     * Define el valor de la propiedad fecFinCitt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecFinCitt(String value) {
        this.fecFinCitt = value;
    }

    /**
     * Obtiene el valor de la propiedad fecFinSubSolic.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecFinSubSolic() {
        return fecFinSubSolic;
    }

    /**
     * Define el valor de la propiedad fecFinSubSolic.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecFinSubSolic(String value) {
        this.fecFinSubSolic = value;
    }

    /**
     * Obtiene el valor de la propiedad fecIniCitt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecIniCitt() {
        return fecIniCitt;
    }

    /**
     * Define el valor de la propiedad fecIniCitt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecIniCitt(String value) {
        this.fecIniCitt = value;
    }

    /**
     * Obtiene el valor de la propiedad fecIniSubSolic.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecIniSubSolic() {
        return fecIniSubSolic;
    }

    /**
     * Define el valor de la propiedad fecIniSubSolic.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecIniSubSolic(String value) {
        this.fecIniSubSolic = value;
    }

    /**
     * Obtiene el valor de la propiedad fecUsuarioCrea.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecUsuarioCrea() {
        return fecUsuarioCrea;
    }

    /**
     * Define el valor de la propiedad fecUsuarioCrea.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecUsuarioCrea(String value) {
        this.fecUsuarioCrea = value;
    }

    /**
     * Obtiene el valor de la propiedad flgActivoInactivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlgActivoInactivo() {
        return flgActivoInactivo;
    }

    /**
     * Define el valor de la propiedad flgActivoInactivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlgActivoInactivo(String value) {
        this.flgActivoInactivo = value;
    }

    /**
     * Obtiene el valor de la propiedad flgSectorEducacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlgSectorEducacion() {
        return flgSectorEducacion;
    }

    /**
     * Define el valor de la propiedad flgSectorEducacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlgSectorEducacion(String value) {
        this.flgSectorEducacion = value;
    }

    /**
     * Obtiene el valor de la propiedad ipUsuarioCrea.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpUsuarioCrea() {
        return ipUsuarioCrea;
    }

    /**
     * Define el valor de la propiedad ipUsuarioCrea.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpUsuarioCrea(String value) {
        this.ipUsuarioCrea = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreCompleto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Define el valor de la propiedad nombreCompleto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreCompleto(String value) {
        this.nombreCompleto = value;
    }

    /**
     * Obtiene el valor de la propiedad numArmada.
     * 
     */
    public int getNumArmada() {
        return numArmada;
    }

    /**
     * Define el valor de la propiedad numArmada.
     * 
     */
    public void setNumArmada(int value) {
        this.numArmada = value;
    }

    /**
     * Obtiene el valor de la propiedad numCitt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumCitt() {
        return numCitt;
    }

    /**
     * Define el valor de la propiedad numCitt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumCitt(String value) {
        this.numCitt = value;
    }

    /**
     * Obtiene el valor de la propiedad numDeclJurada.
     * 
     */
    public int getNumDeclJurada() {
        return numDeclJurada;
    }

    /**
     * Define el valor de la propiedad numDeclJurada.
     * 
     */
    public void setNumDeclJurada(int value) {
        this.numDeclJurada = value;
    }

    /**
     * Obtiene el valor de la propiedad numDiasCitt.
     * 
     */
    public int getNumDiasCitt() {
        return numDiasCitt;
    }

    /**
     * Define el valor de la propiedad numDiasCitt.
     * 
     */
    public void setNumDiasCitt(int value) {
        this.numDiasCitt = value;
    }

    /**
     * Obtiene el valor de la propiedad numDiasSub.
     * 
     */
    public int getNumDiasSub() {
        return numDiasSub;
    }

    /**
     * Define el valor de la propiedad numDiasSub.
     * 
     */
    public void setNumDiasSub(int value) {
        this.numDiasSub = value;
    }

    /**
     * Obtiene el valor de la propiedad numDocBenef.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumDocBenef() {
        return numDocBenef;
    }

    /**
     * Define el valor de la propiedad numDocBenef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumDocBenef(String value) {
        this.numDocBenef = value;
    }

    /**
     * Obtiene el valor de la propiedad numMontoPagad.
     * 
     */
    public double getNumMontoPagad() {
        return numMontoPagad;
    }

    /**
     * Define el valor de la propiedad numMontoPagad.
     * 
     */
    public void setNumMontoPagad(double value) {
        this.numMontoPagad = value;
    }

    /**
     * Obtiene el valor de la propiedad numMontoSolic.
     * 
     */
    public double getNumMontoSolic() {
        return numMontoSolic;
    }

    /**
     * Define el valor de la propiedad numMontoSolic.
     * 
     */
    public void setNumMontoSolic(double value) {
        this.numMontoSolic = value;
    }

    /**
     * Obtiene el valor de la propiedad numRUCEmple.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumRUCEmple() {
        return numRUCEmple;
    }

    /**
     * Define el valor de la propiedad numRUCEmple.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumRUCEmple(String value) {
        this.numRUCEmple = value;
    }

    /**
     * Obtiene el valor de la propiedad razonSocial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * Define el valor de la propiedad razonSocial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazonSocial(String value) {
        this.razonSocial = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoEmpleado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    /**
     * Define el valor de la propiedad tipoEmpleado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoEmpleado(String value) {
        this.tipoEmpleado = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoSeguro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoSeguro() {
        return tipoSeguro;
    }

    /**
     * Define el valor de la propiedad tipoSeguro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoSeguro(String value) {
        this.tipoSeguro = value;
    }

    /**
     * Obtiene el valor de la propiedad zonaUpe.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZonaUpe() {
        return zonaUpe;
    }

    /**
     * Define el valor de la propiedad zonaUpe.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZonaUpe(String value) {
        this.zonaUpe = value;
    }

}

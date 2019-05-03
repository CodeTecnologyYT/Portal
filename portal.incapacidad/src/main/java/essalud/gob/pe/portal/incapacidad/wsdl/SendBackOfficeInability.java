
package essalud.gob.pe.portal.incapacidad.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para sendBackOfficeInability complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="sendBackOfficeInability">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ideSolicitud" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tpDocTitularSia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nrDocBeneficiario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="user" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendBackOfficeInability", propOrder = {
    "ideSolicitud",
    "tpDocTitularSia",
    "nrDocBeneficiario",
    "codUsuario",
    "user",
    "pass"
})
public class SendBackOfficeInability {

    protected int ideSolicitud;
    protected String tpDocTitularSia;
    protected String nrDocBeneficiario;
    protected String codUsuario;
    protected String user;
    protected String pass;

    /**
     * Obtiene el valor de la propiedad ideSolicitud.
     * 
     */
    public int getIdeSolicitud() {
        return ideSolicitud;
    }

    /**
     * Define el valor de la propiedad ideSolicitud.
     * 
     */
    public void setIdeSolicitud(int value) {
        this.ideSolicitud = value;
    }

    /**
     * Obtiene el valor de la propiedad tpDocTitularSia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpDocTitularSia() {
        return tpDocTitularSia;
    }

    /**
     * Define el valor de la propiedad tpDocTitularSia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpDocTitularSia(String value) {
        this.tpDocTitularSia = value;
    }

    /**
     * Obtiene el valor de la propiedad nrDocBeneficiario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNrDocBeneficiario() {
        return nrDocBeneficiario;
    }

    /**
     * Define el valor de la propiedad nrDocBeneficiario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNrDocBeneficiario(String value) {
        this.nrDocBeneficiario = value;
    }

    /**
     * Obtiene el valor de la propiedad codUsuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodUsuario() {
        return codUsuario;
    }

    /**
     * Define el valor de la propiedad codUsuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodUsuario(String value) {
        this.codUsuario = value;
    }

    /**
     * Obtiene el valor de la propiedad user.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Define el valor de la propiedad user.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

    /**
     * Obtiene el valor de la propiedad pass.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPass() {
        return pass;
    }

    /**
     * Define el valor de la propiedad pass.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPass(String value) {
        this.pass = value;
    }

}

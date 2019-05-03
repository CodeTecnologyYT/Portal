
package essalud.gob.pe.portal.incapacidad.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para updateStatusInability complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="updateStatusInability">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codSolicitud" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="opcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tpdoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nrdoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "updateStatusInability", propOrder = {
    "codSolicitud",
    "opcion",
    "tpdoc",
    "nrdoc",
    "user",
    "pass"
})
public class UpdateStatusInability {

    protected String codSolicitud;
    protected String opcion;
    protected String tpdoc;
    protected String nrdoc;
    protected String user;
    protected String pass;

    /**
     * Obtiene el valor de la propiedad codSolicitud.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodSolicitud() {
        return codSolicitud;
    }

    /**
     * Define el valor de la propiedad codSolicitud.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodSolicitud(String value) {
        this.codSolicitud = value;
    }

    /**
     * Obtiene el valor de la propiedad opcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpcion() {
        return opcion;
    }

    /**
     * Define el valor de la propiedad opcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpcion(String value) {
        this.opcion = value;
    }

    /**
     * Obtiene el valor de la propiedad tpdoc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpdoc() {
        return tpdoc;
    }

    /**
     * Define el valor de la propiedad tpdoc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpdoc(String value) {
        this.tpdoc = value;
    }

    /**
     * Obtiene el valor de la propiedad nrdoc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNrdoc() {
        return nrdoc;
    }

    /**
     * Define el valor de la propiedad nrdoc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNrdoc(String value) {
        this.nrdoc = value;
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


package essalud.gob.pe.portal.incapacidad.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para sendReimbursementPayment complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="sendReimbursementPayment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tpDocTitular" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nrDocTitular" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rucEmpleador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecIniSubs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecFinSubs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tpPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "sendReimbursementPayment", propOrder = {
    "tpDocTitular",
    "nrDocTitular",
    "rucEmpleador",
    "fecIniSubs",
    "fecFinSubs",
    "tpPago",
    "user",
    "pass"
})
public class SendReimbursementPayment {

    protected String tpDocTitular;
    protected String nrDocTitular;
    protected String rucEmpleador;
    protected String fecIniSubs;
    protected String fecFinSubs;
    protected String tpPago;
    protected String user;
    protected String pass;

    /**
     * Obtiene el valor de la propiedad tpDocTitular.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpDocTitular() {
        return tpDocTitular;
    }

    /**
     * Define el valor de la propiedad tpDocTitular.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpDocTitular(String value) {
        this.tpDocTitular = value;
    }

    /**
     * Obtiene el valor de la propiedad nrDocTitular.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNrDocTitular() {
        return nrDocTitular;
    }

    /**
     * Define el valor de la propiedad nrDocTitular.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNrDocTitular(String value) {
        this.nrDocTitular = value;
    }

    /**
     * Obtiene el valor de la propiedad rucEmpleador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRucEmpleador() {
        return rucEmpleador;
    }

    /**
     * Define el valor de la propiedad rucEmpleador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRucEmpleador(String value) {
        this.rucEmpleador = value;
    }

    /**
     * Obtiene el valor de la propiedad fecIniSubs.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecIniSubs() {
        return fecIniSubs;
    }

    /**
     * Define el valor de la propiedad fecIniSubs.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecIniSubs(String value) {
        this.fecIniSubs = value;
    }

    /**
     * Obtiene el valor de la propiedad fecFinSubs.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecFinSubs() {
        return fecFinSubs;
    }

    /**
     * Define el valor de la propiedad fecFinSubs.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecFinSubs(String value) {
        this.fecFinSubs = value;
    }

    /**
     * Obtiene el valor de la propiedad tpPago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpPago() {
        return tpPago;
    }

    /**
     * Define el valor de la propiedad tpPago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpPago(String value) {
        this.tpPago = value;
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

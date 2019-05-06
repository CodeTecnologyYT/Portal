
package gob.pe.essalud.appincapacidad.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para subLactanciaBean complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="subLactanciaBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codCUI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codEDocBeneficiario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codEDocHijo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codEDocUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codETipoParto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codTDocBeneficiario" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="codTDocHijo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="numDocBeneficiario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numDocHijo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numDocUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subLactanciaBean", propOrder = {
    "codCUI",
    "codEDocBeneficiario",
    "codEDocHijo",
    "codEDocUsuario",
    "codETipoParto",
    "codTDocBeneficiario",
    "codTDocHijo",
    "numDocBeneficiario",
    "numDocHijo",
    "numDocUsuario"
})
public class SubLactanciaBean {

    protected String codCUI;
    protected String codEDocBeneficiario;
    protected String codEDocHijo;
    protected String codEDocUsuario;
    protected String codETipoParto;
    protected Integer codTDocBeneficiario;
    protected Integer codTDocHijo;
    protected String numDocBeneficiario;
    protected String numDocHijo;
    protected String numDocUsuario;

    /**
     * Obtiene el valor de la propiedad codCUI.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodCUI() {
        return codCUI;
    }

    /**
     * Define el valor de la propiedad codCUI.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodCUI(String value) {
        this.codCUI = value;
    }

    /**
     * Obtiene el valor de la propiedad codEDocBeneficiario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodEDocBeneficiario() {
        return codEDocBeneficiario;
    }

    /**
     * Define el valor de la propiedad codEDocBeneficiario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodEDocBeneficiario(String value) {
        this.codEDocBeneficiario = value;
    }

    /**
     * Obtiene el valor de la propiedad codEDocHijo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodEDocHijo() {
        return codEDocHijo;
    }

    /**
     * Define el valor de la propiedad codEDocHijo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodEDocHijo(String value) {
        this.codEDocHijo = value;
    }

    /**
     * Obtiene el valor de la propiedad codEDocUsuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodEDocUsuario() {
        return codEDocUsuario;
    }

    /**
     * Define el valor de la propiedad codEDocUsuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodEDocUsuario(String value) {
        this.codEDocUsuario = value;
    }

    /**
     * Obtiene el valor de la propiedad codETipoParto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodETipoParto() {
        return codETipoParto;
    }

    /**
     * Define el valor de la propiedad codETipoParto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodETipoParto(String value) {
        this.codETipoParto = value;
    }

    /**
     * Obtiene el valor de la propiedad codTDocBeneficiario.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodTDocBeneficiario() {
        return codTDocBeneficiario;
    }

    /**
     * Define el valor de la propiedad codTDocBeneficiario.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodTDocBeneficiario(Integer value) {
        this.codTDocBeneficiario = value;
    }

    /**
     * Obtiene el valor de la propiedad codTDocHijo.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodTDocHijo() {
        return codTDocHijo;
    }

    /**
     * Define el valor de la propiedad codTDocHijo.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodTDocHijo(Integer value) {
        this.codTDocHijo = value;
    }

    /**
     * Obtiene el valor de la propiedad numDocBeneficiario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumDocBeneficiario() {
        return numDocBeneficiario;
    }

    /**
     * Define el valor de la propiedad numDocBeneficiario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumDocBeneficiario(String value) {
        this.numDocBeneficiario = value;
    }

    /**
     * Obtiene el valor de la propiedad numDocHijo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumDocHijo() {
        return numDocHijo;
    }

    /**
     * Define el valor de la propiedad numDocHijo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumDocHijo(String value) {
        this.numDocHijo = value;
    }

    /**
     * Obtiene el valor de la propiedad numDocUsuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumDocUsuario() {
        return numDocUsuario;
    }

    /**
     * Define el valor de la propiedad numDocUsuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumDocUsuario(String value) {
        this.numDocUsuario = value;
    }

}

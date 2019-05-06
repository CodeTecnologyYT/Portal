package gob.pe.essalud.appincapacidad.comunes.service.impl;

import gob.pe.essalud.appincapacidad.incapacidad.model.Asegurado;
import gob.pe.essalud.appincapacidad.comunes.service.ComunService;

import gob.pe.essalud.appincapacidad.incapacidad.util.WebServiceProperties;
import gob.pe.essalud.appincapacidad.wsdl.OSPEVirtual;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.namespace.QName;
import java.net.URL;

@Service
public class ComunServiceImpl implements ComunService {

    private final static  String USERWSDL="Ospe";
    private final static  String PASSWORDWSDL="essalud";
    private final static String URLWSDLBAZAN="http://172.20.0.188:8080/WSOspeVirtual/OSPEVirtual?wsdl";
    private final static String URLWSDL2="http://10.0.0.194:8080/WSOspeVirtual/OSPEVirtual?wsdl";
    private final  String URLWSDL;
    private final  String NAMESPACE;
    private final  String LOCALPATH;


    @Autowired
    public ComunServiceImpl(WebServiceProperties WSproperties){
        this.URLWSDL=WSproperties.getUrlwsdl();
        this.NAMESPACE=WSproperties.getNamespace();
        this.LOCALPATH=WSproperties.getLocalpath();
    }



    @Override
    public String getRazonSocial(String ruc) {
        return null;
    }

    @Override
    public String getDatosAsegurado(Asegurado asegurado)  {
        String nombreCompleto="";
        try {
            URL url = new URL(URLWSDL);
            QName qname = new QName(
                    "http://controller.ospe.essalud.gob.pe/",
                    "OSPEVirtual");
            javax.xml.ws.Service service = javax.xml.ws.Service.create(url, qname);
            OSPEVirtual servicePort = service.getPort(OSPEVirtual.class);
            String resultado = servicePort.getInsuredData(asegurado.getTpdoc_aseg(), asegurado.getNrdoc_aseg(), USERWSDL, PASSWORDWSDL);
            JSONObject obj = new JSONObject(resultado);
            String apellidoPaterno = obj.getJSONObject("data").getString("apPaterno");
            String apellidoMaterno = obj.getJSONObject("data").getString("apMaterno");
            String nombres = obj.getJSONObject("data").getString("nombres");
            nombreCompleto = nombres  + " " + apellidoPaterno+" " + apellidoMaterno;
            //LOG.info("Cliente: " + cliente.toString());
        }catch (Exception ex){

        }
        return nombreCompleto;
    }
}

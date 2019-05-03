package essalud.gob.pe.portal.incapacidad.comunes.service.impl;

import essalud.gob.pe.portal.incapacidad.comunes.service.ComunService;
import essalud.gob.pe.portal.incapacidad.incapacidad.model.Asegurado;

import essalud.gob.pe.portal.incapacidad.wsdl.OSPEVirtual;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.xml.namespace.QName;
import java.net.URL;

@Service
public class ComunServiceImpl implements ComunService {

    private final static  String USERWSDL="Ospe";
    private final static  String PASSWORDWSDL="essalud";
    private final static String URLWSDLBAZAN="http://172.20.0.188:8080/WSOspeVirtual/OSPEVirtual?wsdl";
    private final static String URLWSDL2="http://10.0.0.194:8080/WSOspeVirtual/OSPEVirtual?wsdl";
    private final static String URLWSDL="http://10.0.29.34:8080/WSOspeVirtual/OSPEVirtual?wsdl";

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

package essalud.gob.pe.portal.incapacidad.incapacidad.service.impl;

import essalud.gob.pe.portal.incapacidad.incapacidad.model.*;
import essalud.gob.pe.portal.incapacidad.incapacidad.service.GlobalService;
import essalud.gob.pe.portal.incapacidad.incapacidad.util.FechaSolicitudUtil;
import essalud.gob.pe.portal.incapacidad.wsdl.OSPEVirtual;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class GlobalServiceImpl implements GlobalService {

    private final static String USERWSDL="Ospe";
    private final static String PASSWORDWSDL="essalud";
    private final static String TIPOSUBSIDIO="IN";
    private final static Integer pasoLaEvaluacion=1;
    private final static Integer nopasoLaEvaluacion=0;
    private boolean solicitudpresentadaantes=true;
    private boolean solicitudpresentadaDespues=false;
    private final static String SOLICITADO="S";
    private final static String URLWSDLBAZAN="http://172.20.0.188:8080/WSOspeVirtual/OSPEVirtual?wsdl";
    private final static String URLWSDL2="http://10.0.0.194:8080/WSOspeVirtual/OSPEVirtual?wsdl";
    private final static String URLWSDL="http://10.0.29.34:8080/WSOspeVirtual/OSPEVirtual?wsdl";

    private final static String ESTADO_ELIMINAR="D";
    private final static String ESTADO_ENVIADO="U";
    private final static String TIPO_PAGO_REEMBOLSO="E";


    @Autowired
    FechaSolicitudUtil fechaUtil;

    public Integer EvualarVeintePrimerosDias(SolicitudIncapacidad solicitud){
        //boolean tiene20Dias=tieneVeinte(asegurado);
        Integer paso20DiasEvaluacion;
        Map<String,Object> mapResult= tieneVeinte(solicitud);
        Integer diasFaltantes= (Integer)mapResult.get("diasFaltantes");
        if((Integer)mapResult.get("esVeinteDias")==0 ){
            if(solicitud.getListprim20d()!=null){
                if((Integer)mapResult.get("diasval20")==1){
                    paso20DiasEvaluacion=pasoLaEvaluacion;
                }else{
                    paso20DiasEvaluacion=nopasoLaEvaluacion;
                }
            }else{
                paso20DiasEvaluacion=nopasoLaEvaluacion;
            }
        }else{
            paso20DiasEvaluacion=pasoLaEvaluacion;
        }
        return paso20DiasEvaluacion;
    }

    public boolean verificar20PrimerosDias(List<Citt> citts20PrimerosDias,Integer diasFaltantes){
        String FechaInicioPeriodo=null;
        String FechaFinPeriodo=null;
        Long cantidadDias=0L;
        Long cantidadDiasTotales=0L;
        Long diasFaltantesLong=new Long(diasFaltantes);
        boolean marcado=true;
        for(Citt citt:citts20PrimerosDias) {
            if (marcado) {
                marcado = false;
                FechaInicioPeriodo = citt.getFecini_citt();
                FechaFinPeriodo = citt.getFecfin_citt();
                cantidadDias = fechaUtil.diasEntreFechas(FechaInicioPeriodo, FechaFinPeriodo);
            } else {
                if (fechaUtil.seCruzanFechas(FechaFinPeriodo, citt.getFecini_citt())) {
                    if (!fechaUtil.seCruzanFechas(FechaInicioPeriodo, citt.getFecini_citt())) {
                        cantidadDias = fechaUtil.diasEntreFechas(citt.getFecini_citt(), citt.getFecfin_citt());
                    } else {
                        cantidadDias = fechaUtil.diasEntreFechas(FechaInicioPeriodo, citt.getFecfin_citt());
                    }
                } else {
                    cantidadDias = fechaUtil.diasEntreFechas(citt.getFecini_citt(), citt.getFecfin_citt());
                }
            }
            cantidadDiasTotales=cantidadDiasTotales+cantidadDias;
        }
        if(cantidadDiasTotales>diasFaltantesLong){
            return true;
        }else{
            return false;
        }
    }

    public Map<String,Object> tieneVeinte(SolicitudIncapacidad solicitud){
        Map<String,Object> map= new HashMap<>();
        EntidadEmpleadora entidadEmpleadora= new EntidadEmpleadora();
        entidadEmpleadora.setNrruc("20410016070");
        entidadEmpleadora.setRzsocial("ESSALUD");
        solicitud.setEntidad(entidadEmpleadora);
        try{
            URL url = new URL(URLWSDL);
            QName qname = new QName(
                    "http://controller.ospe.essalud.gob.pe/",
                    "OSPEVirtual");
            javax.xml.ws.Service service = javax.xml.ws.Service.create(url, qname);
            OSPEVirtual servicePort = service.getPort(OSPEVirtual.class);
            String fechasInicioS;
            String fechaFinS;
            try{
                fechasInicioS= fechaUtil.dateString(solicitud.getFecini_sol());
                fechaFinS= fechaUtil.dateString(solicitud.getFecfin_sol());
            }catch (NullPointerException p){
                fechasInicioS="";
                fechaFinS="";
            }
            System.out.println(solicitud.toString());
            String result=servicePort.get20Days(solicitud.getAsegurado().getTpdoc_aseg(),solicitud.getAsegurado().getNrdoc_aseg(),TIPOSUBSIDIO,fechasInicioS,fechaFinS,USERWSDL,PASSWORDWSDL);
            System.out.println("resultado "+result);
            JSONObject jsonResultado= new JSONObject(result);
            List<Citt> listaCitts= new ArrayList<>();
            Integer error= jsonResultado.getInt("error");
            Integer indicarVeinteDias=jsonResultado.getInt("ind20days");
            Integer cantidadDiasFaltantes = jsonResultado.getInt("diasfalt20");
            Integer diasval20 = jsonResultado.getInt("diasval20");
            String año20Dias = jsonResultado.getString("anio20dias");
            map.put("esVeinteDias",indicarVeinteDias);
            map.put("diasFaltantes",cantidadDiasFaltantes);
            map.put("listaVeinteDias",new ArrayList<>());
            map.put("anio20dias",año20Dias);
            map.put("diasval20",diasval20);
            if(error==0){
                JSONArray listaCittJSON= jsonResultado.getJSONArray("data");
                for (int i = 0; i < listaCittJSON.length(); i++) {
                    Citt citt =new Citt();
                    JSONObject cittObject = listaCittJSON.getJSONObject(i);
                    citt.setNrcitt(cittObject.getString("citt"));
                    citt.setFecini_citt(cittObject.getString("fecInicio"));
                    citt.setFecfin_citt(cittObject.getString("fecFin"));
                    citt.setSeleccionado(true);
                    listaCitts.add(citt);
                }
                map.put("listaVeinteDias",listaCitts);
            }
        }catch(MalformedURLException ex){

        }

        return map;
    }

    public boolean verificarFinTramite(Date fechaFinSolicitud){
        Date fechaActual= new Date(System.currentTimeMillis());
        if(fechaFinSolicitud.getTime()>fechaActual.getTime()){
            return solicitudpresentadaantes;
        }else{
            return solicitudpresentadaDespues;
        }
    }

    public Map esPagoReembolso(SolicitudIncapacidad solicitud) throws MalformedURLException {
        Map mapResult= new HashMap();
        URL url = new URL(URLWSDL);
        QName qname = new QName(
                "http://controller.ospe.essalud.gob.pe/",
                "OSPEVirtual");
        javax.xml.ws.Service service = javax.xml.ws.Service.create(url, qname);
        OSPEVirtual servicePort = service.getPort(OSPEVirtual.class);
        String jsonResult=servicePort.sendReimbursementPayment(solicitud.getAsegurado().getTpdoc_aseg(),solicitud.getAsegurado().getNrdoc_aseg(),solicitud.getEntidad().getNrruc(),fechaUtil.dateString(solicitud.getFecinitemporal()),fechaUtil.dateString(solicitud.getFecfintemporal()),TIPO_PAGO_REEMBOLSO,USERWSDL,PASSWORDWSDL);
        JSONObject jsonresult= new JSONObject(jsonResult);
        System.out.println("Datos "+ jsonResult);
        if(jsonresult.getInt("error")==0){
            JSONObject jsonDetail= jsonresult.getJSONObject("data");
            mapResult.put("pagoReembolso",jsonDetail.getString("pov_es_pago_reembolso"));
            mapResult.put("afiliadoSCTR",jsonDetail.getString("pov_es_ctr"));
            mapResult.put("mensaje",jsonDetail.getString("pov_mensaje_evaluar"));
            System.out.println("Se Registro con exito");
        }

        return mapResult;
    }

    @Override
    public Boolean insertIncapacidad(SolicitudIncapacidad solicitud) throws MalformedURLException  {
        Boolean registroExisto=true;
        URL url = new URL(URLWSDL);
        QName qname = new QName(
                "http://controller.ospe.essalud.gob.pe/",
                "OSPEVirtual");
        javax.xml.ws.Service service = javax.xml.ws.Service.create(url, qname);
        JSONObject json = new JSONObject(solicitud);
        System.out.println(json.toString());
        OSPEVirtual servicePort = service.getPort(OSPEVirtual.class);
        String jsonResult=servicePort.sendInability(json.toString(),USERWSDL,PASSWORDWSDL);
        JSONObject jsonresult= new JSONObject(jsonResult);
        System.out.println("Datos "+ jsonResult);
        if(jsonresult.getInt("error")==0){
            System.out.println("Se Registro con exito");
            registroExisto=false;
        }else{
            System.out.println("No se Registro con exito");
            registroExisto=true;
        }
        return registroExisto;
    }

    @Override
    public List<Citt> listaCitt(Asegurado asegurado,String ruc){
        List<Citt> listaCitts= new ArrayList<>();
        try{
            URL url = new URL(URLWSDL);
            QName qname = new QName(
                    "http://controller.ospe.essalud.gob.pe/",
                    "OSPEVirtual");
            javax.xml.ws.Service service = javax.xml.ws.Service.create(url, qname);
            OSPEVirtual servicePort = service.getPort(OSPEVirtual.class);
            String resuLtado=servicePort.getCITT(asegurado.getTpdoc_aseg(),asegurado.getNrdoc_aseg(),TIPOSUBSIDIO,USERWSDL,PASSWORDWSDL);
            System.out.println(resuLtado);
            JSONObject obj = new JSONObject(resuLtado);
            Integer error=obj.getInt("error");
            if(error==0){
                JSONArray listaCittJSON= obj.getJSONArray("data");
                for (int i = 0; i < listaCittJSON.length(); i++) {
                    Citt citt =new Citt();
                    JSONObject cittObject = listaCittJSON.getJSONObject(i);
                    citt.setNrcitt(cittObject.getString("citt"));
                    citt.setFecini_citt(cittObject.getString("fecInicio"));
                    citt.setFecfin_citt(cittObject.getString("fecFin"));
                    citt.setSeleccionado(false);
                    listaCitts.add(citt);
                }

            }
        }catch (Exception ex){

        }
        return listaCitts;
    }

    @Override
    public List<Solicitud> getListaIncapacidad(UsuarioBean usuario,String tipoSolicitud,String tipoEstado) throws MalformedURLException  {
        URL url = new URL(URLWSDL);
        QName qname = new QName(
                "http://controller.ospe.essalud.gob.pe/",
                "OSPEVirtual");
        javax.xml.ws.Service service = javax.xml.ws.Service.create(url, qname);
        OSPEVirtual servicePort = service.getPort(OSPEVirtual.class);
        String jsonResult=servicePort.reportInability(usuario.getTpdoc_user(),usuario.getNrdoc_user(),tipoSolicitud,tipoEstado,USERWSDL,PASSWORDWSDL);
        JSONObject jsonresult= new JSONObject(jsonResult);
        System.out.println("Datos "+ jsonResult);
        List<Solicitud> solicitudes=new ArrayList<Solicitud>();
        if(jsonresult.getInt("error")==0){
            JSONArray listaCittJSON= jsonresult.getJSONArray("detail");

            for (int i = 0; i < listaCittJSON.length(); i++) {
                Solicitud solicitud =new Solicitud();
                JSONObject solicitudObject = listaCittJSON.getJSONObject(i);
                solicitud.setCitt(solicitudObject.getString("citt"));
                solicitud.setCodigoSolicitud(solicitudObject.getInt("codSolicitud"));
                solicitud.setFechaCese(solicitudObject.getString("fecCese"));
                solicitud.setNombre(solicitudObject.getString("nombre"));
                solicitud.setRuc(solicitudObject.getString("ruc"));
                try {
                    solicitud.setFechaInicioSolicitud(fechaUtil.stringaDate(solicitudObject.getString("feciniSol")));
                    solicitud.setFechaFinSolicitud(fechaUtil.stringaDate(solicitudObject.getString("fecfinSol")));
                    solicitud.setFechaInicioVacaciones(fechaUtil.stringaDate(solicitudObject.getString("feciniVac")));
                    solicitud.setFechaFinVacaciones(fechaUtil.stringaDate(solicitudObject.getString("fecfinVac")));

                }catch(Exception ex){

                }
                solicitud.setMonto(solicitudObject.getString("numMontoSol"));
                solicitud.setTipoDocumneto(solicitudObject.getString("tipDoc"));
                solicitud.setNumeroDocumento(solicitudObject.getString("nroDoc"));
                solicitud.setRazonSocial(solicitudObject.getString("rzSocial"));
                solicitudes.add(solicitud);
            }
        }
        return solicitudes;
    }

    public boolean deleteSolicitud(String idSolicitud,UsuarioBean usuario) throws MalformedURLException {
        boolean eliminado=false;
        URL url = new URL(URLWSDL);
        QName qname = new QName(
                "http://controller.ospe.essalud.gob.pe/",
                "OSPEVirtual");
        javax.xml.ws.Service service = javax.xml.ws.Service.create(url, qname);
        OSPEVirtual servicePort = service.getPort(OSPEVirtual.class);
        String jsonResult=servicePort.updateStatusInability(idSolicitud,ESTADO_ELIMINAR,usuario.getTpdoc_user(),usuario.getNrdoc_user(),USERWSDL,PASSWORDWSDL);
        JSONObject jsonresult= new JSONObject(jsonResult);
        Integer error=jsonresult.getInt("error");
        if(error==0){
            eliminado=true;
        }
        return eliminado;
    }

    public boolean enviarSolicitud(UsuarioBean usuario) throws MalformedURLException {
        boolean enviado=false;
        URL url = new URL(URLWSDL);
        QName qname = new QName(
                "http://controller.ospe.essalud.gob.pe/",
                "OSPEVirtual");
        javax.xml.ws.Service service = javax.xml.ws.Service.create(url, qname);
        OSPEVirtual servicePort = service.getPort(OSPEVirtual.class);
        String jsonResult=servicePort.updateStatusInability("0",ESTADO_ENVIADO,usuario.getTpdoc_user(),usuario.getNrdoc_user(),USERWSDL,PASSWORDWSDL);
        JSONObject jsonresult= new JSONObject(jsonResult);
        Integer error=jsonresult.getInt("error");
        if(error==0){
            enviado=true;
        }
        return enviado;

    }
}

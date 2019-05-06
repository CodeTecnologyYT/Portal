package gob.pe.essalud.appincapacidad.incapacidad.service.impl;

import gob.pe.essalud.appincapacidad.incapacidad.model.*;
import gob.pe.essalud.appincapacidad.seguridad.model.UsuarioLogueado;
import gob.pe.essalud.appincapacidad.incapacidad.service.GlobalService;
import gob.pe.essalud.appincapacidad.incapacidad.service.IncapacidadService;
import gob.pe.essalud.appincapacidad.incapacidad.util.FechaSolicitudUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class IncapacidadServiceImpl implements IncapacidadService {

    private static final String SOLICITADO="S";


    @Autowired
    GlobalService globalService;


    public Resultado listaCitt(Asegurado asegurado, String ruc){
        Resultado resultado=new Resultado();
        try{
            List<Citt> listaCitt=globalService.listaCitt(asegurado,ruc);
            if(listaCitt.size()>0){
                resultado.setFlag(1);
                resultado.setObject(listaCitt);
            }else{
                resultado.setFlag(0);
            }
        }catch (MalformedURLException e){

        }
        return resultado;
    }


    public Resultado tiene20PrimerosDias(SolicitudIncapacidad solicitud){
        Resultado resultado=new Resultado();
        Map mapResult=globalService.tieneVeinte(solicitud);
        resultado.setFlag(1);
        resultado.setObject(mapResult);
        return resultado;
    }

    public Resultado validarSolicitud(SolicitudIncapacidad solicitud){
        Resultado resultado=new Resultado();
        List<ErrorBean> mapErrors= validacionPrevia(solicitud);
        ErrorBean error;
        if(mapErrors.size()>0){
            resultado.setFlag(0);
            resultado.setObject(mapErrors);
        }else{
            Integer evaluacion20primerosDias=globalService.EvualarVeintePrimerosDias(solicitud);
            if(evaluacion20primerosDias==0){
                resultado.setFlag(0);
                error = new ErrorBean("20DiasNoCumple","No cumple con los veinte primeros dias");
                mapErrors.add(error);
                resultado.setObject(mapErrors);
            }
            if(evaluacion20primerosDias==1){
                boolean estaSiendoPresentadoAntes=globalService.verificarFinTramite(solicitud.getFecfin_sol());
                if(estaSiendoPresentadoAntes){
                    resultado.setFlag(0);
                    error = new ErrorBean("presentadoAntes","Esta Siendo presentado antes de finalizar el citt");
                    mapErrors.add(error);
                    resultado.setObject(mapErrors);
                }else{
                    try {
                        Map resultadoReembolso=globalService.esPagoReembolso(solicitud);
                        String reemmbolso= resultadoReembolso.get("pagoReembolso").toString();
                        Integer pagoreembolso=Integer.parseInt(reemmbolso);
                        String sctr= resultadoReembolso.get("afiliadoSCTR").toString();
                        Integer tieneSctr= Integer.parseInt(sctr);
                        switch(pagoreembolso){
                            case 0:
                                //ENVIAR A BACK OFFICE
                                error = new ErrorBean("backoficce","Solicitud no corresponde a Reembolso");
                                mapErrors.add(error);
                                resultado.setFlag(0);
                                resultado.setObject(mapErrors);
                                break;
                            case 1:
                                //APROBADO PARA PAGO
                                if(tieneSctr==1){
                                    if(solicitud.getTieneSctr()==null){
                                        //Mostramos Modal
                                        error = new ErrorBean("sctr","Asegurado cuenta con SCTR");
                                        mapErrors.add(error);
                                        resultado.setFlag(0);
                                        resultado.setObject(mapErrors);
                                    }else{
                                        resultado.setFlag(1);
                                        //insertamos
                                    }
                                }else{
                                    //insertamos Solicitud
                                    resultado.setFlag(1);
                                }
                                break;
                            case 2:
                                //APROBADO PERO ES CAS O TRABAJADOR OBRERO
                                if(tieneSctr==1){
                                    if(solicitud.getTieneSctr()==null){
                                        //Mostramos Modal
                                        error = new ErrorBean("sctr","Asegurado cuenta con SCTR");
                                        mapErrors.add(error);
                                        resultado.setFlag(0);
                                        resultado.setObject(mapErrors);
                                    }else{
                                        //insertamos
                                        resultado.setFlag(1);
                                    }
                                }else{
                                    //insertamos Solicitud
                                    resultado.setFlag(1);
                                }
                                break;
                            case 3:
                                //NO TIENE DERECHO AL SUBSIDIO DE REEMBOLSO INCAPACIDAD
                                resultado.setFlag(0);
                                error = new ErrorBean("derecho","Solicitud no corresponde a Reembolso");
                                mapErrors.add(error);
                                resultado.setFlag(0);
                                resultado.setObject(mapErrors);
                                break;
                        }
                    }catch (Exception ex){
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }
        return resultado;
    }

    public List<ErrorBean> validacionPrevia(SolicitudIncapacidad solicitud){
        List ltserrors= new ArrayList();
        FechaSolicitudUtil fechaUtil = new FechaSolicitudUtil();
        if(solicitud.getAsegurado().getNrdoc_aseg().trim().equals("") || solicitud.getAsegurado().getNrdoc_aseg().equals(null)){
            ErrorBean error= new ErrorBean("asegurado","El campo asegurado esta nulo");
            ltserrors.add(error);
        }else{
            if(solicitud.getAsegurado().getNrdoc_aseg().length()!=8){
                ErrorBean error= new ErrorBean("asegurado","No cumple con la cantidad de caracteres especificados");
                ltserrors.add(error);
            }
        }
        if(solicitud.getListcitt().size()==0){
            ErrorBean error= new ErrorBean("citt","No ha ingresado los citts solicitados");
            ltserrors.add(error);
        }
        if(solicitud.getFecini_sol()==null || solicitud.getFecfin_sol()==null ){
            ErrorBean error= new ErrorBean("fechaSolictud","No se especificado la fecha de solicitud");
            ltserrors.add(error);
        }else{
            if(fechaUtil.diasEntreFechasDate(solicitud.getFecini_sol() ,  solicitud.getFecfin_sol())<0){
                ErrorBean error= new ErrorBean("citt","Cruce de lo Solicitado con 20 primeros dias");
                ltserrors.add(error);
            }
        }

        if(solicitud.getMonto_sol()==null ){
            ErrorBean error= new ErrorBean("monto","No se especificado el monto a solicitar");
            ltserrors.add(error);
        }
        if(solicitud.getEsSectorPrivado()==1){
            String fechaInicioVacaciones = fechaUtil.dateString(solicitud.getFecini_vac());
            String fechaFinVacaciones = fechaUtil.dateString(solicitud.getFecfin_vac());
            if(fechaInicioVacaciones.equals("") || fechaFinVacaciones.equals("")){
                System.out.println("Errores de Fecha de Vacaciones");
                System.out.println("Fecha Vaciones "+solicitud.getFecfin_vac());
                ErrorBean error= new ErrorBean("fechaVacaciones","Especifica la fecha de Solicitud");
                ltserrors.add(error);
            }
        }
        if(solicitud.getEstaTrabajando()==1){
            String fechaCese = fechaUtil.dateString(solicitud.getFec_cese());
            if(fechaCese.equals("")){
                ErrorBean error= new ErrorBean("fechaCese","Especifica la fecha de Cese");
                ltserrors.add(error);
            }
        }
        if(solicitud.getArchivos().getNombre()==null){
            ErrorBean error = new ErrorBean("archivos1", "No se Agregado Archivo");
            ltserrors.add(error);
        }
        return ltserrors;
    }
    public Resultado registrarSolicitud(SolicitudIncapacidad solicitud){
        Resultado resultado = new Resultado();
        List<ErrorBean> mapErrors= new ArrayList<ErrorBean>();
        try {
            Boolean error=globalService.insertIncapacidad(solicitud);
            if(error){
                resultado.setFlag(0);
                ErrorBean errorbean = new ErrorBean("solicitud","Usted ya presenta una solicitud con este expediente");
                mapErrors.add(errorbean);
                resultado.setObject(mapErrors);
            }else{
                resultado.setFlag(1);
            }
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
        return resultado;
    }
    public Resultado getListaIncapacidadSolicitado(UsuarioLogueado usuario, String tipo){
        Resultado resultado = new Resultado();
        List<Solicitud> solicitud;
        try{
            solicitud = globalService.getListaIncapacidad(usuario,SOLICITADO,tipo);

           if(solicitud==null){
               resultado.setFlag(0);
           }else{
               resultado.setFlag(1);
               resultado.setObject(solicitud);
           }
        } catch (MalformedURLException e) {
            resultado.setFlag(0);
        }
        return resultado;
    }

    public Resultado deleteSolicitudIncapacidad(String idSolicitud,UsuarioLogueado usuario){
        Resultado resultado= new Resultado();
        boolean eiiminado;
        try {
            eiiminado = globalService.deleteSolicitud(idSolicitud,usuario);
        }catch (MalformedURLException ex){
            eiiminado=false;
        }
        if(eiiminado){
            resultado.setFlag(1);
        }else{
            resultado.setFlag(0);
        }
        return resultado;
    }

    public Resultado enviarSolicitudIncapacidad(UsuarioLogueado usuario,String tipo){
        Resultado resultado= new Resultado();
        boolean seEnvioExito=true;
        try {
            List<Solicitud> solicitud = globalService.getListaIncapacidad(usuario,SOLICITADO,tipo);
            if(solicitud.size()>0){
                seEnvioExito = globalService.enviarSolicitud(usuario);
            }else{
                seEnvioExito=false;
            }
        }catch (MalformedURLException ex){
            seEnvioExito=false;
        }
        if(seEnvioExito){
            resultado.setFlag(1);
        }else{
            resultado.setFlag(0);
        }
        return resultado;
    }

}


package gob.pe.essalud.appincapacidad.incapacidad.util;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

@Service
public class FechaSolicitudUtil {

    private final long milesecondsdays = 24 * 60 * 60 * 1000L;
    public Date stringaDate(String fecha) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date= simpleDateFormat.parse(fecha);
        return new java.sql.Date(date.getTime());
    }
    public String dateString(Date fecha) {
        String date="";
        try {
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            date = simpleDateFormat.format(fecha);
        }catch (NullPointerException e){
            date="";
        }
        return date;
    }
    public Long diasEntreFechas(String fechaInicio,String fechaFin){
        Long diasCalculados=0l;
        try {
            Date fechaInicioDate = stringaDate(fechaInicio);
            Date fechaFinDate = stringaDate(fechaFin);
            diasCalculados = (fechaFinDate.getTime() - fechaInicioDate.getTime()) / milesecondsdays;
        }catch (ParseException e){

        }
        return diasCalculados+1;
    }
    public Long diasEntreFechasDate(Date fechaInicio,Date fechaFin){
        Long diasCalculados = (fechaFin.getTime() - fechaInicio.getTime()) / milesecondsdays;

        return diasCalculados+1;
    }
    public boolean seCruzanFechas(String fechaInicio,String fechaFin){
        Long cantidadDias=diasEntreFechas(fechaInicio,fechaFin);
        return cantidadDias > 0;
    }
}

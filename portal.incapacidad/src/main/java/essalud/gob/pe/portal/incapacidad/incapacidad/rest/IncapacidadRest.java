package essalud.gob.pe.portal.incapacidad.incapacidad.rest;

import essalud.gob.pe.portal.incapacidad.incapacidad.model.*;
import essalud.gob.pe.portal.incapacidad.incapacidad.service.IncapacidadService;
import essalud.gob.pe.portal.incapacidad.incapacidad.util.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;
@RestController
@RequestMapping("/api/incapacidad/")
public class IncapacidadRest {

    private static final String LISTARBORRADORES="2";
    private static final String LISTARENVIADOS="0";
    @Bean
    @Order(0)
    public MultipartFilter multipartFilter() {
        MultipartFilter multipartFilter = new MultipartFilter();
        multipartFilter.setMultipartResolverBeanName("multipartResolver");
        return multipartFilter;
    }

    @Autowired
    private IncapacidadService incapacidadService;

    @Autowired
    private FileSystemStorageService storageService;

    @PostMapping(value="/tieneVeinteDias")
    public ResponseEntity<Resultado> tieneVienteDias(@RequestBody SolicitudIncapacidad solicitud){
        Resultado resultado=incapacidadService.tiene20PrimerosDias(solicitud);
        return new ResponseEntity<Resultado>(resultado,HttpStatus.ACCEPTED);
    }

    @PostMapping(value="/enviarSolicitud")
    public ResponseEntity<Resultado> grabarSolicitud(@RequestPart("solicitud")  SolicitudIncapacidad solicitud, @RequestPart(value="file",required = false) MultipartFile files ){
        System.out.println("Solicitud "+solicitud.toString());
        UsuarioBean usuario = new UsuarioBean();
        usuario.setTpdoc_user("06");
        usuario.setNrdoc_user("20410016070");
        solicitud.setUser(usuario);
        solicitud.setTp_sub("IN");
        Resultado resultado= incapacidadService.validarSolicitud(solicitud);
        Archivo archivo;
        if(resultado.getFlag()!=0){
            archivo = storageService.store(files);
            solicitud.setArchivos(archivo);
            solicitud.setTieneSctr(0);
            Resultado resultadoJson=incapacidadService.registrarSolicitud(solicitud);
            resultado = resultadoJson;
        }
        return new ResponseEntity<Resultado>(resultado,HttpStatus.ACCEPTED);
    }

    @PostMapping(value="/listaCitt")
    public ResponseEntity<Resultado> listaCitt(@RequestBody Asegurado asegurado){
        String ruc="20410016070";
        Resultado resultado= incapacidadService.listaCitt(asegurado,ruc);
        return new ResponseEntity<Resultado>(resultado,HttpStatus.ACCEPTED);
    }
    @GetMapping(value="/listaDetallada")
    public ResponseEntity<Resultado> listaDetalle(){
        UsuarioBean usuario = new UsuarioBean();
        usuario.setTpdoc_user("06");
        usuario.setNrdoc_user("20410016070");
        Resultado resultado= incapacidadService.getListaIncapacidadSolicitado(usuario,LISTARBORRADORES);
        return new ResponseEntity<Resultado>(resultado,HttpStatus.ACCEPTED);
    }
    @DeleteMapping(value="/listaDetallada/del/{idSolicitud}")
    public ResponseEntity<Resultado> deleteDetalle(@PathVariable Integer idSolicitud){
        UsuarioBean usuario = new UsuarioBean();
        usuario.setTpdoc_user("06");
        usuario.setNrdoc_user("20410016070");
        Resultado resultado= incapacidadService.deleteSolicitudIncapacidad(idSolicitud.toString(),usuario);
        return new ResponseEntity<Resultado>(resultado,HttpStatus.ACCEPTED);
    }
    @PutMapping(value="/enviarSolicitud")
    public ResponseEntity<Resultado>enviarSolicitud(){
        UsuarioBean usuario = new UsuarioBean();
        usuario.setTpdoc_user("06");
        usuario.setNrdoc_user("20410016070");
        Resultado resultado=incapacidadService.enviarSolicitudIncapacidad(usuario,LISTARBORRADORES);
        return new ResponseEntity<Resultado>(resultado,HttpStatus.ACCEPTED);
    }

    @GetMapping(value="/listaDetalladaSolicitado")
    public ResponseEntity<Resultado> listaSolicitud(){
        UsuarioBean usuario = new UsuarioBean();
        usuario.setTpdoc_user("06");
        usuario.setNrdoc_user("20410016070");
        Resultado resultado= incapacidadService.getListaIncapacidadSolicitado(usuario,LISTARENVIADOS);
        return new ResponseEntity<Resultado>(resultado,HttpStatus.ACCEPTED);
    }
}

package gob.pe.essalud.appincapacidad.incapacidad.rest;

import gob.pe.essalud.appincapacidad.incapacidad.model.*;
import gob.pe.essalud.appincapacidad.incapacidad.service.IncapacidadService;
import gob.pe.essalud.appincapacidad.incapacidad.util.FileSystemStorageService;
import gob.pe.essalud.appincapacidad.seguridad.model.UsuarioLogueado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.http.HttpSession;
import java.io.IOException;

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
    public ResponseEntity<Resultado> tieneVienteDias(@RequestBody SolicitudIncapacidad solicitud, HttpSession httpSession){
        UsuarioLogueado usuario = (UsuarioLogueado) httpSession.getAttribute("usuarioLogeado");
        EntidadEmpleadora entidadEmpleadora= new EntidadEmpleadora();
        entidadEmpleadora.setNrruc(usuario.getRucEmpleador());
        solicitud.setEntidad(entidadEmpleadora);
        Resultado resultado=incapacidadService.tiene20PrimerosDias(solicitud);
        return new ResponseEntity<Resultado>(resultado,HttpStatus.ACCEPTED);
    }

    @PostMapping(value="/enviarSolicitud")
    public ResponseEntity<Resultado> grabarSolicitud(@RequestPart("solicitud")  SolicitudIncapacidad solicitud, @RequestPart(value="file",required = false) MultipartFile files , HttpSession httpSession){
        System.out.println("Solicitud "+solicitud.toString());
        UsuarioLogueado usuario = (UsuarioLogueado) httpSession.getAttribute("usuarioLogeado");
        solicitud.setUser(usuario);
        solicitud.setTp_sub("IN");
        Resultado resultado= incapacidadService.validarSolicitud(solicitud);
        Archivo archivo;
        if(resultado.getFlag()!=0){
            try {
                archivo = storageService.store(files);
                solicitud.setArchivos(archivo);
                solicitud.setTieneSctr(0);
                Resultado resultadoJson=incapacidadService.registrarSolicitud(solicitud);
                resultado = resultadoJson;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<Resultado>(resultado,HttpStatus.ACCEPTED);
    }

    @PostMapping(value="/listaCitt")
    public ResponseEntity<Resultado> listaCitt(@RequestBody Asegurado asegurado, HttpSession httpSession){
        UsuarioLogueado usuario = (UsuarioLogueado) httpSession.getAttribute("usuarioLogeado");
        String ruc= usuario.getRucEmpleador();
        Resultado resultado= incapacidadService.listaCitt(asegurado,ruc);
        return new ResponseEntity<Resultado>(resultado,HttpStatus.ACCEPTED);
    }
    @GetMapping(value="/listaDetallada")
    public ResponseEntity<Resultado> listaDetalle( HttpSession httpSession){
        UsuarioLogueado usuario = (UsuarioLogueado) httpSession.getAttribute("usuarioLogeado");
        Resultado resultado= incapacidadService.getListaIncapacidadSolicitado(usuario,LISTARBORRADORES);
        return new ResponseEntity<Resultado>(resultado,HttpStatus.ACCEPTED);
    }
    @DeleteMapping(value="/listaDetallada/del/{idSolicitud}")
    public ResponseEntity<Resultado> deleteDetalle(@PathVariable Integer idSolicitud, HttpSession httpSession){
        UsuarioLogueado usuario = (UsuarioLogueado) httpSession.getAttribute("usuarioLogeado");
        Resultado resultado= incapacidadService.deleteSolicitudIncapacidad(idSolicitud.toString(),usuario);
        return new ResponseEntity<Resultado>(resultado,HttpStatus.ACCEPTED);
    }
    @PutMapping(value="/enviarSolicitud")
    public ResponseEntity<Resultado>enviarSolicitud( HttpSession httpSession){
        UsuarioLogueado usuario = (UsuarioLogueado) httpSession.getAttribute("usuarioLogeado");
        Resultado resultado=incapacidadService.enviarSolicitudIncapacidad(usuario,LISTARBORRADORES);
        return new ResponseEntity<Resultado>(resultado,HttpStatus.ACCEPTED);
    }

    @GetMapping(value="/listaDetalladaSolicitado")
    public ResponseEntity<Resultado> listaSolicitud(HttpSession httpSession){
        UsuarioLogueado usuario = (UsuarioLogueado) httpSession.getAttribute("usuarioLogeado");
        Resultado resultado= incapacidadService.getListaIncapacidadSolicitado(usuario,LISTARENVIADOS);
        return new ResponseEntity<Resultado>(resultado,HttpStatus.ACCEPTED);
    }
}

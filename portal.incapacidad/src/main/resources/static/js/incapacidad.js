var filesSustento = new Map();
var cittDisponibles =new Array();
var citt20Dias =new Array();
var cantidadDiasFaltantes=0;
var sctr=0;
var año20Dias=0;
$(document).ready(function() {

    listaSolicitudes(0);

    //Inicializar Inputs de Fecha
    $('#fechaInicioVacaciones').datepicker({
        format: 'dd/mm/yyyy'
    });
    $('#fechaFinVacaciones').datepicker({
        format: 'dd/mm/yyyy'
    });
    $('#fechaCese').datepicker({
        format: 'dd/mm/yyyy'
    });

    /**************************************************************************/
    /* ******************************** Inputs ********************************/
    /**************************************************************************/

    //radiosbutton
    $(".checkround").on("click", function () {
        var seleccionado = $(this).siblings("input[type='radio']").prop("checked");
        $(this).siblings("input[type='radio']").prop("checked", !seleccionado);
    });

    $('#asegurado_numeroDocumento').on('input', function () {
        this.value = this.value.replace(/[^0-9]/g,'');
    });
    $('#montoSolicitado').on('input', function () {
        this.value = this.value.replace(/[^0-9]/g,'');
    });

    /**************************************************************************/
    /* ********************** Datos Asegurado *********************************/
    /**************************************************************************/

    $("#asegurado_numeroDocumento").keyup(function(){
        var numeroDocumento=$(this).val();
        var tipoDocumento=$("#asegurado_tipoDocumento").val();
        var asegurado={};
        if(numeroDocumento !=''  && numeroDocumento!=null){
            $("#inputSCTR").hide();
            $(".asegurado_nombre").html("");
            $(".section_citt_solicitados").html(crearEtiquetaNoFoundCittSolicitados());
            $(".section_periodo").html(crearEtiquetaFoundPeriodo());
            $("#seccion_20Dias").hide();
            $(".diasSolicitados").html("0");
            sctr=0;
            if(numeroDocumento.length==8){
                //$(".asegurado_nombre").html(traerNombreAsegurado(tipoDocumento,numeroDocumento));
                asegurado.tpdoc_aseg=tipoDocumento;
                asegurado.nrdoc_aseg=numeroDocumento;
                traerNombreAsegurado(asegurado);
                cittDisponibles=listaCitts(asegurado);
            }else{
                $(".asegurado_nombre").html("Nombres y Apellidos del Asegurado");
            }
        }
    });

    $("input").change(function(){
        $(this).removeClass("input_error");
        $(this).parent().parent().find("label").removeClass("label_error");
        $(this).parent().parent().find(".error_message").hide();
        $(this).parent().find(".icon_error").hide();
        $(this).parent().parent().parent().find(".error_message").hide();
        $(this).parent().parent().parent().find(".error_message").hide();
        $(this).parent().find(".input-group-addon").removeClass("error-date");
    });


    /**************************************************************************/
    /* *********************** Citt Solicitados****************************** */
    /**************************************************************************/

    $(".section_citt_solicitados").on("click", ".section_item .section_close", function () {
        var padre = $(this).parent();
        var idSolicitado = $(padre).attr("data-atrr");
        cittDisponibles.forEach((element, index) => {
            if (element.nrcitt === idSolicitado) {
                cittDisponibles[index].seleccionado = false;
            }
        });
        var cantidad=verificarNotieneCittSolicitados(cittDisponibles);
        if(cantidad>0){
            if(verificaContinuidad(cittDisponibles)){
                calcularPeriodo(cittDisponibles,citt20Dias,cantidadDiasFaltantes,año20Dias);
                animacionOcultar(padre);
            }else{
                cittDisponibles.forEach((element, index) => {
                    if (element.nrcitt === idSolicitado) {
                        cittDisponibles[index].seleccionado = true;
                    }
                });
                abrirModal("#mensajesSolicitud");
                $("#mensajesSolicitud .text-error").html("No puede eliminar este citt ya no tendrian fechas continuas");
            }
        }else{
            $(".section_citt_solicitados").html(crearEtiquetaNoFoundCittSolicitados());
            $(".section_periodo").html(crearEtiquetaFoundPeriodo());
        }
    });

    $(".section_citt_20PrimerosDias").on("click", ".section_item .section_close", function () {
        var padre = $(this).parent();
        var idSolicitado = $(padre).attr("data-atrr");
        citt20Dias.forEach((element, index) => {
            if (element.numeroCitt === idSolicitado) {
                citt20Dias[index].seleccionado = false;
            }
        });
        animacionOcultar(padre);
        calcularPeriodo(cittDisponibles,citt20Dias,cantidadDiasFaltantes,año20Dias);
    });

    /*********************************************************/
    /* **************** Citt Disponibles ******************* */
    /*********************************************************/

    $("#mostrarCittDisponiblesModal").on("click", function () {

        $("#error_cittDisponibles").hide();
        var asegurado={};
        asegurado.tpdoc_aseg=$("#asegurado_tipoDocumento").val().trim();
        asegurado.nrdoc_aseg=$("#asegurado_numeroDocumento").val().trim();

        if(asegurado.tpdoc_aseg=="" &&  asegurado.nrdoc_aseg==""){
            abrirModal("#mensajesSolicitud");
            $("#mensajesSolicitud .text-error").html("Ingresar primero el numero de Documento");
        }else{
            if(cittDisponibles.length>0){
                crearListaCittDisponiblesSolicitados(cittDisponibles);
                abrirModal("#CittDisponiblesSoliModal");
            }else{
                abrirModal("#mensajesModal");
            }
        }

    });

    $(".section_citt_content").on("click", ".btn-solicitar", function () {
        var idSolicitar = $(this).attr("data-atrr");

        cittDisponibles.forEach((element, index) => {
            if (element.nrcitt === idSolicitar) {
                cittDisponibles[index].seleccionado = !cittDisponibles[index].seleccionado;
                if (cittDisponibles[index].seleccionado) {
                    checkSeleccionadoSolicitado(this,idSolicitar);
                } else {
                    checkDeseleccionadoSolicitado(this,idSolicitar);
                }
            }
        });
    });

    $(".guardar-citt").on("click", function () {

        var asegurado={};
        asegurado.tpdoc_aseg=$("#asegurado_tipoDocumento").val() ;
        asegurado.nrdoc_aseg=$("#asegurado_numeroDocumento").val();
        var cantidad=verificarNotieneCittSolicitados(cittDisponibles);
        if(cantidad>0){
            if(verificaContinuidad(cittDisponibles)){
                agregarCittSolicitados(cittDisponibles);
                calcularPeriodo(cittDisponibles,citt20Dias,cantidadDiasFaltantes,año20Dias);
                cerrarModal("#CittDisponiblesSoliModal");
                var solicitud=extraerDatosSolicitud(cittDisponibles,citt20Dias);
                tiene20Dias(solicitud);
            }
            else{
                abrirModal("#mensajesSolicitud");
                $("#mensajesSolicitud .text-error").html("Los citts solicitados no son continuos");
            }
        }else{
            $(".section_citt_solicitados").html(crearEtiquetaNoFoundCittSolicitados());
            $(".section_periodo").html(crearEtiquetaFoundPeriodo());
        }
    });

    /*************************************************************************/
    /* ******************** Datos Adicionales ********************************/
    /*************************************************************************/

    $(".esDocente").on("click",function() {
        var esDocente=$("input[name='esDocente']:checked").val();

        if(esDocente==1){
            animacionMostrar("#sectionDocente");
        }else{
            animacionOcultar("#sectionDocente");
        }
    });

    $(".estaTrabajando").on("click",function() {
        var estaTrabajando=$("input[name='estaTrabajando']:checked").val();

        if(estaTrabajando==1){
            animacionMostrar("#sectionCesado");
        }else{
            animacionOcultar("#sectionCesado");
        }
    });

    /*************************************************************************/
    /* ******************* Documentos de Sustento ************************** */
    /*************************************************************************/
    $("#btn-sustento1").on("click",function(){
        $("#error_documentoSustento").hide();
        $("#sustentosfile1").val('');
        $("#sustentosfile1").click();
    });

    $("#sustentosfile1").on("change",function(){
        var file=$("#sustentosfile1").get(0).files[0];
        var json = {};
        var ext = file.name.split('.').pop().toLowerCase();
        if(ext=='pdf'){
            json.tipo="sustento1";
            json.nombre=file.name;
            json.file=file;
            filesSustento.set("sustento1",json);
            $(".secction_files1").html("");
            $(".secction_files1").append(crearEtiquetaFiles(file));

        }else{
            abrirModal("#mensajesSolicitud");
            $("#mensajesSolicitud .text-error").html("no es pdf");
        }
    });

    /************************************************************************/
    /* ************************Botones Principáles************************* */
    /************************************************************************/
    //Enviar Solicitud
    // $("#btn-revisar").on("click",function() {
    //     abrirModal("#revisarModal");
    // });
    //
    $("#btn_limpiar").on("click",function() {
        limpiarFormulario();
    });

    /*********************************************************/
    /* ************* Acciones Modales Adicionales ************/
    /*********************************************************/
    $("#btn-revisar").on("click",function() {
        //cerrarModal("#revisarModal");
        var solicitud=extraerDatosSolicitud(cittDisponibles ,citt20Dias);
        //console.log("Solicitudes",solicitudes);
        //agregarNuevaSolicitud(solicitudes);
        //actualizarCantidad(solicitudes.length);
        enviarSolicitudAjax(solicitud);
        //enviarSolicitudAjax(solicitud);
        //abrirModal("#exitoModal");
    });

    $("#aceptar20Dias").on("click",function(){
        $("#mensaje20Dias").html(crearEtiquetaMensajeTiene20Dias());
        console.log("ciittDisponibles",cittDisponibles);
        console.log("citt20Dias",citt20Dias);
        calcularPeriodo(cittDisponibles,citt20Dias,cantidadDiasFaltantes,año20Dias);
    });
    $("#noaceptar20Dias").on("click",function(){
        citt20Dias=null;
        $("#mensaje20Dias").html(crearEtiquetaNotiene20Dias());
    });
    $("#mensaje20Dias").on("click","#ver20PrimerosDias",function(){
        abrirModal("#20DiasModal");
    });
    $("#mensaje20Dias").on("click","#aceptar20PrimerosDias",function(){
        abrirModal("#advertenciaModal");
    });
    $(".content-fixed .content-list").on("click",'.btn_eliminar_solicitud',function(){
        var idSolicitud=$(this).attr("atr-data");
        deleteIDSolicitud(idSolicitud);
    });
});

function crearEtiquetaMensajeTiene20Dias(){
    var htmlInject ='<span>Usted tiene veinte Dias</span>\n' +
        '<a style="cursor: pointer;text-decoration: underline;" id="ver20PrimerosDias">Ver Citts</a>';
    return htmlInject;
}
function crearEtiquetaNotiene20Dias(){
    var htmlInject= '<span>Usted no cuemta con los veinte primeros aceptar las condiciones</span>\n' +
        '<a style="cursor: pointer;text-decoration: underline;" id="aceptar20PrimerosDias">Aceptar Condiciones</a>';
    return htmlInject;
}

function crearEtiquetaNoFoundCittSolicitados(){
    var htmlInject='<div class="item_no_cargado">\n' +
    '                                 No tiene CITT Solicitados cargados aun...\n' +
    '                             </div>';
    return htmlInject;
}
function crearEtiquetaFoundPeriodo(){
    var htmlInject ='<div class="item_no_cargado" style="padding: 18px;width: 100%;">\n' +
        '                    No tiene periodo Seleccionado\n' +
        '                </div>';
    return htmlInject;
}
function crearEtiquetaHayCruceDeDias(){
    var htmlInject ='<div class="item_no_cargado" style="padding: 18px;width: 100%;">\n' +
        '                    Su periodo solicitado se cruza con los veinte primeros Dias\n' +
        '                </div>';
    return htmlInject;
}

function crearEtiquetaPeriodo(){
    var htmlInject='<div class="contenedor_inputs">\n' +
        '                    <label class="label">Fecha de inicio Solicitud</label>\n' +
        '                    <div  id=\'fechaInicioSolicitud\' style="width: 200px">\n' +
        '\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '                <div class="contenedor_inputs">\n' +
        '                    <label class="label">Fecha de fin Solicitud</label>\n' +
        '                    <div id=\'fechaFinSolicitud\' style="width: 200px">\n' +
        '\n' +
        '                    </div>\n' +
        '                </div>';
    return htmlInject;
}

function crearEtiquetaCittSolicitados(jsonCittSolicitados){
    var id="S"+jsonCittSolicitados.nrcitt;

    var htmlInject="<div class=\"section_item\" data-atrr="+jsonCittSolicitados.nrcitt+" id='"+id+"' style='display:none'>\n" +
"                               <div class=\"section_title\">"+jsonCittSolicitados.nrcitt+"</div>\n" +
"                               <div class=\"section_close\"><i class=\"fas fa-times\"></i></div>\n" +
"                               <div class=\"section_information\">\n" +
"                                   <div class=\"section-image\">\n" +
"                                       <i class=\"fas fa-notes-medical\"></i>\n" +
"                                   </div>\n" +
"                                   <div class=\"section-data\">\n" +
"                                      <div> Fecha inicio: "+jsonCittSolicitados.fecini_citt+"</div>\n" +
"                                      <div> Fecha fin:    "+jsonCittSolicitados.fecfin_citt+"</div>\n" +
"                                   </div>\n" +
"                               </div>\n" +
"                            </div>";
    return htmlInject;
}

function crearEtiquetaCittDisponibles(jsonCittDisponible){
    var htmlInjectButton="";
    var id="D"+jsonCittDisponible.nrcitt;
    htmlInjectButton= "<div style=\"width: 120px;\" data-atrr='"+jsonCittDisponible.nrcitt+"'  id='"+id+"' class=\"btn-solicitar boton-out-primary\">Solicitar</div>\n";
    var FechaInicio=moment(jsonCittDisponible.fecini_citt,'YYYY-MM-DD');
    var FechaFin=moment(jsonCittDisponible.fecfin_citt,'YYYY-MM-DD');
    var htmlInject="<div  class=\"section_citt_item\">\n" +
        "                            <div style=\"margin-right: 28px;\">\n" +
        "                                <divs class=\"section-titulo\">"+ jsonCittDisponible.nrcitt+"</divs>\n" +
        "                                <div class=\"section-texto\">Desde:" +FechaInicio.format("DD/MM/YYYY")+ " hasta " +FechaFin.format("DD/MM/YYYY")+ "</div>\n" +
        "                            </div>\n" +
        "                            <div>\n" +
        htmlInjectButton+
        "                            </div>\n" +
        "                        </div>";

    return htmlInject;
}

function crearEtiquetaFiles(archivo){
    var nombre="";
    if(archivo.name.length>20) nombre=archivo.name.substr(0,20)+"....pdf";
    else nombre=archivo.name;

    var htmlInject="<div style='color: #6b6b6b;padding-top: 10px'>"+
                    nombre
                    +"</div>"
    return htmlInject;
}
function notfoundEtiquetaFiles(){
    var htmlInject="<div class='item_no_cargado'>"+
    "No tiene Ningun Sustento"+
    "</div>";
    return htmlInject;
}


function notfoundItemAsegurado(){
    var htmlInject="<div style=\"color: #737373;text-align: center;\">\n" +
        "                No tiene ni una solicitud aun registrada\n" +
        "            </div>";
    return htmlInject;
}


function crearListaetiquetaItemAsegurado(solicitud){
    var classEtiqueta="";
    var idSolicitud=solicitud.codigoSolicitud;
    if(idSolicitud%2!=0 ){
        classEtiqueta="content-item_impar";
    }else{
        classEtiqueta="content-item";
    }
    var htmlInject="<div class='"+classEtiqueta+"'>\n" +
        "                <div style='color: #4886fe;letter-spacing: 3px;font-size: 14px;padding: 22px 11px 0px 14px;'>"+solicitud.numeroDocumento+"</div>\n" +
        "                <div style='display: flex;justify-content: center'>\n" +
        "                    <div style='padding: 14px;'>"+ solicitud.nombre+"</div>\n" +
        "                    <div class='btn_eliminar_solicitud' atr-data='"+idSolicitud+"'><i class=\"fas fa-times\"></i></div>\n" +
        "                </div>\n" +
        "            </div>";
    return htmlInject;
}

function crearListaetiquetaModalItem20PrimerosDias(citt20Dias,cantidad){
    var FechaInicio = moment(citt20Dias.fecini_citt,'YYYY-MM-DD');
    var FechaFin = moment(citt20Dias.fecfin_citt,'YYYY-MM-DD');
    var htmlInject="<div class=\"item-20dias\">\n" +
        "                        <div>"+citt20Dias.nrcitt+"</div>\n" +
        "                        <div>"+FechaInicio.format('DD/MM/YYYY')+"</div>\n" +
        "                        <div>"+FechaFin.format('DD/MM/YYYY')+"</div>\n" +
        "                        <div>"+cantidad+"d</div>\n" +
        "                    </div>";
    return htmlInject;
}

function cantidadDiasEvaluacion(FechaInicio,FechaFin,añoEvaluacion){
    var añoInicio=FechaInicio.format("YYYY");
    var añoFin=FechaFin.format("YYYY");
    var ultimoAñoEvaluacion=moment("31/12/"+añoEvaluacion,"DD/MM/YYYY");
    var primerAñoEvaluacion=moment("01/01/"+añoEvaluacion,"DD/MM/YYYY");
    var cantidadDias=0;
    if(añoInicio==añoFin){
        cantidadDias =FechaFin.diff(FechaInicio,'days')+1;
    }else{
        if(añoInicio==añoEvaluacion){
            cantidadDias=ultimoAñoEvaluacion.diff(FechaInicio,'days')+1;
        }
        else{
            cantidadDias=FechaFin.diff(primerAñoEvaluacion,'days')+1;
        }
    }
    return cantidadDias;
}

function agregarNuevaSolicitud(solicitudes){
    $(".content-fixed .content-list").html("");
    if(solicitudes.length>0){
        solicitudes.forEach(function(element,index){
            $(".content-fixed .content-list").append(crearListaetiquetaItemAsegurado(element));
        });
    }else{
        $(".content-fixed .content-list").html(notfoundItemAsegurado());
    }
}

function crearLista20primerosDiasModal(citts20dias,cantidadDiasFaltantes,año20dias){
    $(".group-items").html("");
    citts20dias.forEach(function(element,index){
        console.log("elemento",element);
        console.log("año 20",año20dias);
        var resultado=actualizarCittSubsidios(element.fecini_citt,element.fecfin_citt,año20dias);
        var FechaInicioCitt=resultado.setFechaInicio();
        var FechaFinCitt=resultado.setFechaFin();
        console.log("Fechas",FechaInicioCitt+ " " +FechaFinCitt);
        var cantidad=cantidadDiasEvaluacion(moment(FechaInicioCitt,'YYYY-MM-DD'),moment(FechaFinCitt,'YYYY-MM-DD'),año20dias);
        if(cantidadDiasFaltantes>cantidad){
            $(".group-items").append(crearListaetiquetaModalItem20PrimerosDias(element,cantidad));
            cantidadDiasFaltantes=cantidadDiasFaltantes-cantidad;
        }else{
            $(".group-items").append(crearListaetiquetaModalItem20PrimerosDias(element,cantidadDiasFaltantes));
        }
    });
}

function crearListaCittDisponiblesSolicitados(ltscittDisponibles){

    $(".section_citt_content").html("");
    ltscittDisponibles.forEach(function(element,index){
        $(".section_citt_content").append(crearEtiquetaCittDisponibles(element));
        if(element.seleccionado){
            checkSeleccionadoSolicitado("#D"+element.nrcitt,element.nrcitt);
        }else{
            checkDeseleccionadoSolicitado("#D"+element.nrcitt,element.nrcitt);
        }

    });
}


function agregarCittSolicitados(ltscittSolicitados){

    $(".section_citt_solicitados").html("");
    ltscittSolicitados.forEach(function(elements,index){
        if(elements.seleccionado){

            var htmlSeccion=crearEtiquetaCittSolicitados(elements,0);
            $(".section_citt_solicitados").append(htmlSeccion);
            animacionMostrar("#S"+elements.nrcitt);
        }
    });
}

function checkSeleccionadoSolicitado(etiqueta,idSolicitado){
    $("#D"+idSolicitado).html("Solicitado");
    $( etiqueta ).addClass("boton-primary");
    $( etiqueta ).removeClass("boton-out-primary");
}

function checkDeseleccionadoSolicitado(etiqueta,idSolicitado){
    $("#D"+idSolicitado).html("Solicitar");
    $( etiqueta ).removeClass("boton-primary");
    $( etiqueta ).addClass("boton-out-primary");
}

function animacionMostrar(etiqueta){
    console.log("entro animacion mostrar etiqueta",etiqueta);
    $(etiqueta).fadeTo(1000,1);
    $(etiqueta).show(1000);
}

function animacionOcultar(etiqueta){
    console.log("entro animacion ocultar etiqueta",etiqueta);
    $(etiqueta).fadeTo(1000,0);
    $(etiqueta).hide(1000);
}

function abrirModal(etiqueta){
    $(etiqueta).modal('show');
}

function cerrarModal(etiqueta){
    $(etiqueta).modal('hide');
}

function correctMessageAsegurado(){
    $("#asegurado_numeroDocumento").removeClass("input_error");
    $("#asegurado_numeroDocumento").parent().parent().find("label").removeClass("label_error");
    $("#asegurado_numeroDocumento").parent().parent().find(".error_message").hide();
    $(".icon_error").hide();
}

function errorMessageAsegurado(){
    $("#asegurado_numeroDocumento").addClass("input_error");
    $("#asegurado_numeroDocumento").parent().parent().find("label").addClass("label_error");
    $("#asegurado_numeroDocumento").parent().parent().find(".error_message").show();
    $(".icon_error").show();
}
function correctoCittSolicitado(){
    $("#error_cittDisponibles").hide();
}
function errorCittSolicitudes(msg){
    $("#error_cittDisponibles").html(msg);
    $("#error_cittDisponibles").show();
}
function correctoFechaVacionales(){
    $("#fechaInicioVacaciones").find("input").removeClass("input_error");
    $("#fechaInicioVacaciones").find("span").removeClass("error-date");
    $("#fechaFinVacaciones").find("input").removeClass("input_error");
    $("#fechaFinVacaciones").find("span").removeClass("error-date");
    $("#error_fechaVacacional").hide();
}
function errorFechasVacionales(){
    $("#fechaInicioVacaciones").find("input").addClass("input_error");
    $("#fechaInicioVacaciones").find("span").addClass("error-date");
    $("#fechaFinVacaciones").find("input").addClass("input_error");
    $("#fechaFinVacaciones").find("span").addClass("error-date");
    $("#error_fechaVacacional").show();
}
function correctoFechaCese(){
    $("#fechaCese").find("input").removeClass("input_error");
    $("#fechaCese").find("span").removeClass("error-date");
    $("#error_fechaCese").hide();
}

function errorFechaCese(){
    $("#fechaCese").find("input").addClass("input_error");
    $("#fechaCese").find("span").addClass("error-date");
    $("#error_fechaCese").show();
}
function correctoMonto(){
    $("#montoSolicitado").removeClass("input_error");
    $("#error_montoSolicitado").hide();
}
function correctoFile(){
    $("#error_documentoSustento1").hide();
    $("#error_documentoSustento2").hide();
    $("#error_documentoSustento3").hide();
}

function errorMonto(){
    $("#montoSolicitado").addClass("input_error");
    $("#error_montoSolicitado").show();
}
function correctoDocumentoSustento(){
    $("#error_documentoSustento").hide();
}
function errorDocumentoSustento(etiqueta,descripcion){
    $(etiqueta).html(descripcion);
    $(etiqueta).show();
}

function cargarFechasSolicitud(fechaInicio,fechaFin){
    $("#fechaInicioSolicitud").html(fechaInicio);
    $("#fechaFinSolicitud").html(fechaFin);
}

function verificarNotieneCittSolicitados(cittDisponibles){
    var cantidad=0;
    cittDisponibles.forEach(function(elements,index){
        if(elements.seleccionado){
            cantidad++;
        }
    });
    return cantidad;
}
function calcularPeriodoSolicitado(cittDisponibles){
    var marcado=true;
    var fechaInicioPeriodo=null;
    var fechaFinPeriodo=moment("1001-01-01","YYYY-MM-DD");
    var periodo={};
    console.log("Disponible estos citt",cittDisponibles);
    cittDisponibles.forEach(function(elements,index){
        console.log(elements);
       if(elements.seleccionado ) {
           var fechaFinElement=moment(elements.fecfin_citt,"YYYY-MM-DD");
           var fechaInicioElement=moment(elements.fecini_citt,"YYYY-MM-DD");
           if(fechaFinElement.diff(fechaFinPeriodo,'days')>0){
               if(marcado){
                   fechaInicioPeriodo=fechaInicioElement;
                   marcado=false;
               }
               fechaFinPeriodo=fechaFinElement;
           }
       }
    });
    if(fechaFinPeriodo!=null && fechaInicioPeriodo!=null){
        periodo.fechaFin= fechaFinPeriodo.format('YYYY-MM-DD');
        periodo.fechaInicio= fechaInicioPeriodo.format('YYYY-MM-DD');
    }else{
        periodo.fechaFin= "";
        periodo.fechaInicio= "";
    }

    return periodo;
}
function calcularPeriodo(listaSolicitado,lista20Dias,cantidadDiasFaltantes,año20dias){
    var periodoSolicitado=calcularPeriodoSolicitado(listaSolicitado);
    console.log("Perido Solicituda ",periodoSolicitado);
    var cantidadRestar=cantidadDeDiasARestar(periodoSolicitado.fechaInicio,periodoSolicitado.fechaFin,lista20Dias,cantidadDiasFaltantes,año20dias);
    console.log("Cantidad de Dias Restar",cantidadRestar);
    var fechaInicioNuevo = moment(periodoSolicitado.fechaInicio,"YYYY-MM-DD").add(cantidadRestar,'d');
    var fechaFinNuevo=moment(periodoSolicitado.fechaFin,"YYYY-MM-DD");
    var cantidadDiasSolicitados=0;
    if(fechaInicioNuevo===undefined || fechaFinNuevo===undefined ){
        $(".section_periodo").html(crearEtiquetaFoundPeriodo());
        $(".diasSolicitados").html(cantidadDiasSolicitados);//cantidad De Dias Solicitados
    }else{
        if(diasEntreFechas(fechaInicioNuevo,fechaFinNuevo)<=0){
            $(".section_periodo").html(crearEtiquetaHayCruceDeDias());
            $(".diasSosection_citt_20PrimerosDiaslicitados").html(cantidadDiasSolicitados);//cantidad De Dias Solicitados
        }
        else{
            $(".section_periodo").html(crearEtiquetaPeriodo());
            cantidadDiasSolicitados=diasEntreFechas(fechaInicioNuevo.format("YYYY-MM-DD"),fechaFinNuevo.format("YYYY-MM-DD"));
            $(".diasSolicitados").html(cantidadDiasSolicitados);
            cargarFechasSolicitud(fechaInicioNuevo.format("DD/MM/YYYY"),fechaFinNuevo.format("DD/MM/YYYY"));
        }
    }
}

function actualizarCantidad(cantidad){
    $(".cantidadAsegurados").html(cantidad);
}

function cantidadDeDiasARestar(FechaInicioSolicitud,FechaFin,lista20Dias,cantidadDiasRestar,año20dias){
    var compvararDias=cantidadDiasRestar;
    var cantidadDeRestar=0;
    var cantidadDias;
    var cantidadCruzada=0;
    var diasRestarSolicitado=0;

    if(lista20Dias!=null){
        if(lista20Dias.length>0){
            var restarSolicitado=DiasRestaralInicio(FechaInicioSolicitud,año20dias);
            diasRestarSolicitado=restarSolicitado.cantidadRestar();
            var FechaInicio = restarSolicitado.fechaSolicitud();
            console.log("Restar Solicitado",diasRestarSolicitado);
            console.log("-------------------------------------------------------------------------");
            console.log("Fecha de inicio " +FechaInicio + "Fecha de Fin " + FechaFin);
            console.log("-------------------------------------------------------------------------");
            lista20Dias.forEach(function(elements,index){
                var resultado=actualizarCittSubsidios(elements.fecini_citt,elements.fecfin_citt,año20dias);
                var FechaInicioCitt=resultado.setFechaInicio();
                var FechaFinCitt=resultado.setFechaFin();
                var validoCitt= resultado.setPertenece20dias();
                var listaCittAnteriores=Array();
                if(elements.seleccionado) {
                    if(validoCitt==1){
                        var cruzamiento=cruceconCittAnteriores(elements,listaCittAnteriores,año20dias);
                        var FechaInicioNuevaCitt=cruzamiento.fechaInicioNuevo();
                        var fechaFinNuevaCitt=cruzamiento.fechaFinNuevo();
                        listaCittAnteriores.push(resultado);

                        if (fechasCruzadas(FechaInicio,fechaFinNuevaCitt) && fechasCruzadas(FechaInicioNuevaCitt, FechaFin) ) {
                            if (diasEntreFechas(FechaInicio,FechaInicioNuevaCitt) >= 0) {
                                cantidadCruzada = diasEntreFechas(FechaInicioNuevaCitt, fechaFinNuevaCitt,año20dias);
                            } else {
                                cantidadCruzada = diasEntreFechas(FechaInicio, fechaFinNuevaCitt,año20dias);
                            }
                            console.log("Fecha Dias CITT " + FechaInicioNuevaCitt + " dias a Restar " + fechaFinNuevaCitt);
                            cantidadDias    = diasEntreFechas(FechaInicioNuevaCitt,fechaFinNuevaCitt,año20dias);
                            console.log("Fecha comparar Dias " + compvararDias + " dias a Restar " + cantidadCruzada);
                            console.log("Cantidad de Dias " + cantidadDias );
                            if (compvararDias <= cantidadCruzada) {
                                cantidadDeRestar += compvararDias;
                            } else {
                                cantidadDeRestar += cantidadCruzada;
                            }

                        } else {
                            cantidadDias = diasEntreFechas(FechaInicioCitt, FechaFinCitt,año20dias);
                        }
                        compvararDias -= cantidadDias;
                    }
                }
            });
        }
        console.log("cantidad de DIas a Restar ", cantidadDeRestar +" Solicitud "+diasRestarSolicitado);
    }
    return cantidadDeRestar+diasRestarSolicitado;
}

function cruceconCittAnteriores(cittActual,listaCittAnteriores,año20dias){
    var cantidadCruzada=0;
    var mayorcruzamiento=0;
    var resultado=actualizarCittSubsidios(cittActual.fecini_citt,cittActual.fecfin_citt,año20dias);
    var FechaInicio=resultado.setFechaInicio();
    var FechaFin=resultado.setFechaFin();
    listaCittAnteriores.forEach(function(elements,index){
        if (fechasCruzadas(cittActual.fecini_citt,elements.setFechaFin()) && fechasCruzadas(elements.setFechaInicio(),cittActual.fecfin_citt) ) {
            if (diasEntreFechas(cittActual.fecini_citt, elements.setFechaInicio()) >= 0) {
                cantidadCruzada = diasEntreFechas(elements.setFechaInicio(), elements.setFechaFin(),año20dias);
            } else {
                cantidadCruzada = diasEntreFechas(cittActual.fecini_citt, elements.setFechaFin(),año20dias);
            }
            FechaInicio= moment(elements.setFechaFin(),'YYYY-MM-DD').diff(1,'days').format('YYYY-MM-DD');
            FechaFin=cittActual.fecfin_citt;
        } else {
            cantidadCruzada =0;
            FechaInicio=elements.fecini_citt;
            FechaFin=cittActual.fecfin_citt;
        }
        if(mayorcruzamiento<cantidadCruzada){
            mayorcruzamiento=cantidadCruzada;
        }
    });
    return {
        diasCruzados: function(){
          return   mayorcruzamiento;
        },
        fechaInicioNuevo:function(){
            return FechaInicio;
        },
        fechaFinNuevo:function(){
            return FechaFin;
        }
    };
}

function DiasRestaralInicio(fechaInicio,año20Dias){
    var fechaIniciotRango1Date = moment(fechaInicio,"YYYY-MM-DD");
    var cantidadRestar=0;
    var fechaInicioSolicitud=fechaInicio;
    if(fechaIniciotRango1Date.format('YYYY')!=año20Dias){
        var ultimoDiasAño =moment('01/01/'+año20Dias,'DD/MM/YYYY');
        cantidadRestar=ultimoDiasAño.diff(fechaIniciotRango1Date,'days');
        fechaInicioSolicitud=ultimoDiasAño.format('YYYY-MM-DD');
    }
    return {
        cantidadRestar:function(){
            return cantidadRestar;
        },
        fechaSolicitud:function(){
            return fechaInicioSolicitud;
        }
    }
}

function actualizarCittSubsidios(fechaInicio,FechaFin,año20Dias,pertenece20Dias){

    var fechaInicioRango2Date = moment(fechaInicio,"YYYY-MM-DD");
    var fechaFintRango1Date = moment(FechaFin,"YYYY-MM-DD");
    var ultimoDiaAño =moment('31/12/'+(año20Dias-1),'DD/MM/YYYY');
    var primerDiaAño =moment('01/01/'+año20Dias,'DD/MM/YYYY');
    if(fechaInicioRango2Date.format("YYYY")==fechaFintRango1Date.format("YYYY")){
        if(fechaInicioRango2Date.format("YYYY")==año20Dias){
            pertenece20Dias=1;
        }else{
            pertenece20Dias=0;
        }
    }else{
        if(fechaInicioRango2Date.format("YYYY")==año20Dias){
            FechaFin=ultimoDiaAño.format("YYYY-MM-DD");
            pertenece20Dias=1;
        }else{
            fechaInicio=primerDiaAño.format("YYYY-MM-DD");
            pertenece20Dias=1;
        }
    }

    console.log("Inicio CITT :",fechaInicio);
    console.log("FIN CITT :",FechaFin);
    console.log("Pertenece :",pertenece20Dias);
    return {
        setPertenece20dias:function(){
            return  pertenece20Dias;
        },
        setFechaInicio:function(){
            return  fechaInicio;
        },
        setFechaFin:function(){
            return FechaFin;
        }
    }
}


function fechasCruzadas(fechaFintRango1,fechaInicioRango2){
    var fechaFintRango1Date = moment(fechaFintRango1,"YYYY-MM-DD");
    var fechaInicioRango2Date = moment(fechaInicioRango2,"YYYY-MM-DD");
    console.log("fecha 1 "+fechaFintRango1+" Fecha 2 "+fechaInicioRango2);
    if(fechaInicioRango2Date.diff(fechaFintRango1Date,'days')>=0){
        console.log("Se cruza en el año");
        return true;
    }else{
        console.log("No se cruza");
        return false;
    }
}

function diasEntreFechas(fecha1,fecha2){
    var fechaInicio = moment(fecha1,"YYYY-MM-DD");
    var fechaFin = moment(fecha2,"YYYY-MM-DD");
    return fechaFin.diff(fechaInicio, 'days')+1;
}

function verificaContinuidad(cittDisponibles) {
    var fechaFinPeriodo;
    var fechaInicioElement;
    var fechaFinElement;
    var marcado=true;
    var result=true;
    cittDisponibles.forEach(function(elements,index){
        if(elements.seleccionado) {
            if(marcado){
                fechaFinPeriodo=moment(elements.fecfin_citt,'YYYY-MM-DD');
                marcado=false;
            }
            else{
                fechaInicioElement=moment(elements.fecini_citt,'YYYY-MM-DD');
                fechaFinElement=moment(elements.fecfin_citt,'YYYY-MM-DD');
                if(fechaInicioElement.diff(fechaFinPeriodo,'days')>1){
                    result=false;
                    return;
                }else{
                    fechaFinPeriodo=fechaFinElement;
                }
            }
        }
    });
    return result;
}
function extraerDatosSolicitud(cittDisponibles,citt20Dias){
    var solicitud={};
    var titular={};
    titular.tpdoc_aseg=$("#asegurado_tipoDocumento").val();
    titular.nrdoc_aseg=$("#asegurado_numeroDocumento").val();
    titular.nombres=$(".asegurado_nombre").html();
    solicitud.asegurado=titular;
    solicitud.listcitt = cittDisponibles;
    solicitud.listprim20d = citt20Dias;
    var periodoSolicitado=calcularPeriodoSolicitado(cittDisponibles);
    if(periodoSolicitado.fechaInicio!="" && periodoSolicitado.fechaFin!=""){
        console.log("Periodo Solicitado "+periodoSolicitado.fechaInicio+" "+periodoSolicitado.fechaFin);
        solicitud.fecinitemporal=moment(periodoSolicitado.fechaInicio,'YYYY-MM-DD').format();
        solicitud.fecfintemporal=moment(periodoSolicitado.fechaFin,'YYYY-MM-DD').format();
        var cantidadRestar=cantidadDeDiasARestar(periodoSolicitado.fechaInicio,periodoSolicitado.fechaFin,citt20Dias,cantidadDiasFaltantes,año20Dias);
        var fechaInicioNuevo = moment(periodoSolicitado.fechaInicio,"YYYY-MM-DD").add(cantidadRestar,'d');
        var fechaFinNuevo=moment(periodoSolicitado.fechaFin,"YYYY-MM-DD");
        solicitud.fecini_sol=fechaInicioNuevo.format();
        solicitud.fecfin_sol=fechaFinNuevo.format();
    }
    solicitud.esSectorPrivado=$("input[name='esDocente']:checked").val();
    if(solicitud.esSectorPrivado==1){
        solicitud.fecini_vac=$("#fechaInicioVacaciones").datepicker('getUTCDate');
        solicitud.fecfin_vac=$("#fechaFinVacaciones").datepicker('getUTCDate');
    }
    solicitud.estaTrabajando=$("input[name='estaTrabajando']:checked").val();
    if(solicitud.estaTrabajando==1){
        solicitud.fec_cese=$("#fechaCese").datepicker('getUTCDate');
    }
    solicitud.monto_sol=$("#montoSolicitado").val();
    if(sctr==1){
        solicitud.tieneSctr=$("input[name='tieneSCTR']:checked").val();
    }else{
        solicitud.tieneSctr=null;
    }

    var archivo={};
    archivo.nombre=filesSustento.get("sustento1")==null?null:filesSustento.get("sustento1").nombre;
    archivo.tipo="sustento1";
    solicitud.archivos=archivo;
    return solicitud;
}

function cantidadDias20Dias(listaVeinteDias){
    var cantidadDias=0;
    listaVeinteDias.forEach(function(element,index){
        cantidadDias+=diasEntreFechas(element.fecini_citt,element.fecfin_citt);
    });
    return cantidadDias;
}
function limpiarFormulario(){
    $("#asegurado_numeroDocumento").val("");
    $(".asegurado_nombre").html("Nombres y Apellidos del Asegurado");
    $("#mensaje20Dias").html("");
    $(".section_citt_solicitados").html(crearEtiquetaNoFoundCittSolicitados());
    $(".diasSolicitados").html("0");
    $(".section_periodo").html(crearEtiquetaFoundPeriodo());
    citt20Dias= new Array();
    cittDisponibles= new Array();
    $("input[name='esDocente']").val(['0']);
    $("#sectionDocente").css("display","none");
    $("input[name='estaTrabajando']").val(['0']);
    $("input[name='tieneSCTR']").val(['0']);
    $("#sectionCesado").css("display","none");
    $("#montoSolicitado").val("");
    $("#fechaInicioVacaciones").datepicker().children('input').val('');
    $("#fechaFinVacaciones").datepicker().children('input').val('');
    $("#fechaCese").datepicker().children('input').val('');
    $("#sectionSCTR").css("display","none");
    $(".secction_files1").html("");
    $("#inputSCTR").hide();
    filesSustento=new Map();
    //errores
    correctoDocumentoSustento();
    correctMessageAsegurado();
    correctoCittSolicitado();
    correctoFechaVacionales();
    correctoFechaCese();
    correctoMonto();
    correctoFile();
    sctr=0;
}
//var urlConstanteDetall=window.location.origin+"/api";

var urlConstanteDetall=window.location.origin+"/portal.incapacidad-0.0.1-SNAPSHOT/api";


function tiene20Dias(solicitud) {
    console.log("solicitud",solicitud);
    $.ajax({
        type: "POST",
        contentType: "application/json",
        accept: 'text/plain',
        dataType: 'text',
        url: urlConstanteDetall + '/incapacidad/tieneVeinteDias',
        data: JSON.stringify(solicitud),
        async: false,
        error: function () {

        },
        success: function (data, href, status) {
            var resultado=JSON.parse(data);
            console.log("citt de 20 dias",resultado);
            if(resultado.object.esVeinteDias==0){
                var cantidadDias=cantidadDias20Dias(resultado.object.listaVeinteDias);
                cantidadDiasFaltantes=resultado.object.diasFaltantes;
                citt20Dias=resultado.object.listaVeinteDias;
                año20Dias=parseInt(resultado.object.anio20dias);
                crearLista20primerosDiasModal(resultado.object.listaVeinteDias,cantidadDiasFaltantes,año20Dias);
                if(cantidadDias>=cantidadDiasFaltantes){
                    abrirModal("#advertenciaModal");
                }
                else{
                    abrirModal("#Falta20DiasModal");
                }
            }
        }
    });
}

function traerNombreAsegurado(asegurado){
    $(".lds-ring").show();
    $(".asegurado_nombre").hide();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        accept: 'text/plain',
        dataType: 'text',
        url: urlConstanteDetall + '/comun/datosAsegurado',
        data: JSON.stringify(asegurado),
        error: function () {

        },
        success: function (data, href, status) {
            $(".lds-ring").hide();
            var resultado=JSON.parse(data);
            console.log(resultado);
            if(resultado.flag==1) {
                if (resultado.object.nombres == "") {
                    $(".asegurado_nombre").html("No existe la persona ingresa")
                } else {
                    $(".asegurado_nombre").html(resultado.object.nombres);
                }
                $(".asegurado_nombre").show();
            }
        }
    });
}

function listaCitts(asegurado){
    $.ajax({
        type: "POST",
        contentType: "application/json",
        accept: 'text/plain',
        dataType: 'text',
        url: urlConstanteDetall + '/incapacidad/listaCitt',
        data: JSON.stringify(asegurado),
        error: function () {

        },
        success: function (data, href, status) {
            var resultado=JSON.parse(data);
            if(resultado.flag==1) {
                //$(".asegurado_nombre").html(resultado.object.nombreCompvaro);
                cittDisponibles=resultado.object;
            }else{
                cittDisponibles=new Array();
            }
        }
    });
}

function enviarSolicitudAjax(solicitud){
    var formData = new FormData();
    formData.append("file",filesSustento.get("sustento1")==null?null:filesSustento.get("sustento1").file);

    var entityJsonStr = JSON.stringify(solicitud);
    formData.append('solicitud',new Blob([entityJsonStr], {
        type: "application/json"
    }));
    console.log("lista de Sustentos",filesSustento);
    console.log("solicitudes",solicitud);
    fetch(urlConstanteDetall + '/incapacidad/enviarSolicitud',{
        method: "post",
        url: urlConstanteDetall + '/incapacidad/enviarSolicitud',
        body: formData
    }).then(response => response.json())
    .then(function (response) {
        if(response.flag==0){
            var listaErrores=response.object;
            listaErrores.forEach(function(elements,index){
               switch (elements.codigo) {
                   case "asegurado":
                       abrirModal("#mensajesSolicitud");
                       $("#mensajesSolicitud .text-error").html("Se encontro un error revisar Solicitud");
                       errorMessageAsegurado();
                       break;
                   case "citt":
                       abrirModal("#mensajesSolicitud");
                       $("#mensajesSolicitud .text-error").html("Se encontro un error revisar Solicitud");
                       errorCittSolicitudes(elements.descripcion);
                       break;
                   case "fechaSolictud":
                       //errorFechasVacionales();
                       break;
                   case "fechaVacaciones":
                       abrirModal("#mensajesSolicitud");
                       $("#mensajesSolicitud .text-error").html("Se encontro un error revisar Solicitud");
                       errorFechasVacionales();
                       break;
                   case "monto":
                       abrirModal("#mensajesSolicitud");
                       $("#mensajesSolicitud .text-error").html("Se encontro un error revisar Solicitud");
                       errorMonto();
                       break;
                   case "fechaCese":
                       abrirModal("#mensajesSolicitud");
                       $("#mensajesSolicitud .text-error").html("Se encontro un error revisar Solicitud");
                       errorFechaCese();
                       break;
                   case "20DiasNoCumple":
                       tiene20Dias(solicitud);
                       break;
                   case "solicitud":
                       abrirModal("#mensajesSolicitud");
                       $("#mensajesSolicitud .text-error").html("Ya se registro Solicitud");
                       break;
                   case "archivos1":
                       abrirModal("#mensajesSolicitud");
                       $("#mensajesSolicitud .text-error").html("Se encontro un error revisar Solicitud");
                       errorDocumentoSustento("#error_documentoSustento1",elements.description);
                       break;
                   case "sctr":
                       abrirModal("#mensajesSolicitud");
                       $("#mensajesSolicitud .text-error").html(elements.descripcion);
                       $("#inputSCTR").show();
                       sctr=1;
                       break;
                   case "derecho":
                   case "backoficce":
                       abrirModal("#mensajesSolicitud");
                       $("#mensajesSolicitud .text-error").html(elements.descripcion);
                       break;
               }
            });
        }else{
            listaSolicitudes(1);
        }
    });
}



function listaSolicitudes(estado){
    $.ajax({
        type: "GET",
        contentType: "application/json",
        accept: 'text/plain',
        dataType: 'text',
        url: urlConstanteDetall + '/incapacidad/listaDetallada',
        error: function () {

        },
        success: function (data, href, status) {
            var resultado=JSON.parse(data);
            if(resultado.flag==1) {
                var solicitudes=resultado.object;
                agregarNuevaSolicitud(solicitudes);
                actualizarCantidad(solicitudes.length);
                if(estado==1){
                    abrirModal("#exitoAddModal");
                }
                limpiarFormulario();
            }else{
                cittDisponibles=new Array();
            }
        }
    });
}

function deleteIDSolicitud(id){
    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        accept: 'text/plain',
        dataType: 'text',
        url: urlConstanteDetall + '/incapacidad/listaDetallada/del/'+id,
        error: function () {

        },
        success: function (data, href, status) {
            var resultado=JSON.parse(data);
            if(resultado.flag==1){
                listaSolicitudes(0);
            }
        }
    });
}
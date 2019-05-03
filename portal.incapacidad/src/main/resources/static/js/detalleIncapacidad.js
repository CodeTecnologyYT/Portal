$(document).ready(function(){
    // var pathname = window.location.pathname;
    // alert(pathname);
    // var URLdomain = window.location.host;
    // alert(URLdomain);
    // var URLhash = window.location.hash;
    // alert(URLhash);
    // var url = window.location.origin;
    // //var filename = url.substring(url.lastIndexOf('/')+1);
    // alert(url);
    enlistarSolicitudes(1);
    $("#btn-enviar").on("click",function(){
        enviarSolicitud();
    });
});
//var urlConstanteDetall=window.location.origin+"/api";
var urlConstanteDetall=window.location.origin+"/portal.incapacidad-0.0.1-SNAPSHOT/api/";
function enlistarSolicitudes(creacion){
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
            console.log("resultado",resultado);
            console.log("transformado",transformarLista(resultado.object));
            if(creacion==1){
                $('#tableDetalleCitt').bootstrapTable({
                    data:transformarLista(resultado.object),
                    columns: [
                        [{
                            field: 'codigoSolicitud',
                            sortable: true,
                            align: 'center',
                            visible:false
                        },{
                            field: 'numeroDocumento',
                            title: 'Numero Documento',
                            sortable: true,
                            align: 'center'
                        },{
                            field: 'nombreCompleto',
                            title: 'Nombres y Apellidos',
                            sortable: true,
                            align: 'center'
                        },{
                            field: 'ruc',
                            title: 'RUC',
                            sortable: true,
                            align: 'center'
                        },{
                            field: 'citts',
                            title: 'Citts Solicitados',
                            sortable: true,
                            align: 'center'
                        },{
                            field: 'fechaSolicitud',
                            title: 'Fecha Solicitud',
                            sortable: true,
                            align: 'center'
                        }, {
                            field: 'diasSolicitados',
                            title: 'Dias Solicitados',
                            sortable: true,
                            align: 'center'
                        }, {
                            field: 'fechaVacional',
                            title: 'Fecha Vacaciones',
                            sortable: true,
                            align: 'center'
                        },{
                            field: 'fechaCese',
                            title: 'Fecha Cese',
                            sortable: true,
                            align: 'center'
                        },{
                            field: 'monto',
                            title: 'Monto',
                            sortable: true,
                            align: 'center'
                        },{
                            field: 'operate',
                            title: 'Opcion',
                            align: 'center',
                            events: window.DeleteEvents,
                            formatter: DeleteFormatter
                        }]
                    ]
                });
            }else{
                $('#tableDetalleCitt').bootstrapTable('load',transformarLista(resultado.object));
            }
        }
    });
}
function enviarSolicitud(){

    $.ajax({
        type: "PUT",
        contentType: "application/json",
        accept: 'text/plain',
        dataType: 'text',
        url: urlConstanteDetall + '/incapacidad/enviarSolicitud',
        error: function () {

        },
        success: function (data, href, status) {
            var resultado=JSON.parse(data);
            console.log("resultado",resultado)
            $('#tableDetalleCitt').bootstrapTable('load',[]);
            if(resultado.flag==1){
                abrirModal("#exitoModal");
            }else{
                //alert("no hay que actualizar");
                abrirModal("#mensajesSolicitud");
                $("#mensajesSolicitud .text-error").html("No hay solicitudes ingresadas");
            }
        }
    });
}
function abrirModal(etiqueta){
    $(etiqueta).modal('show');
}

function cerrarModal(etiqueta){
    $(etiqueta).modal('hide');
}
function transformarLista(solicitudes){
    var listaTransformada=new Array();
    for(var i=0;i<solicitudes.length;i++){
        listaTransformada.push(transformarInformacion(solicitudes[i]));
    }
    return listaTransformada;
}

function transformarInformacion(solicitud){
    var resultado ={};
    resultado.codigoSolicitud=solicitud.codigoSolicitud;
    resultado.numeroDocumento=solicitud.numeroDocumento;
    resultado.nombreCompleto=solicitud.nombre;
    resultado.ruc=solicitud.ruc;
    var citts="";
    var listcitt= solicitud.citt.split(";");
    for(var citt of listcitt){
        citts+=citt+" ";
    }
    resultado.citts=citts;
    var fechaInicioS=moment(solicitud.fechaInicioSolicitud,'YYYY-MM-DD');
    var fechaFinS=moment(solicitud.fechaFinSolicitud,'YYYY-MM-DD');

    resultado.fechaSolicitud= fechaInicioS.format("DD/MM/YYYY")+" "+fechaFinS.format("DD/MM/YYYY");
    resultado.diasSolicitados=fechaFinS.diff(fechaInicioS,"days")+1;
    if(solicitud.esSectorPrivado==1){
        var fechaInicioV=moment(solicitud.fechaInicioVacaciones);
        var fechaFinV=moment(solicitud.fechaFinVacaciones);
        resultado.fechaVacional= fechaInicioV.format("DD/MM/YYYY")+" "+fechaFinV.format("DD/MM/YYYY");
    }
    if(solicitud.estaTrabajando==1){
        var fechaCese=moment(solicitud.fechaCese);
        resultado.fechaCese= fechaCese.format("DD/MM/YYYY");
    }
    resultado.monto= solicitud.monto;
    return resultado;
}
function DeleteFormatter(value, row, index) {
    console.log(row);
    return ['<a attr-data="'+row.codigoSolicitud+'" class="eliminar">Eliminar </a>'].join('');
}

window.DeleteEvents = {
    'click .eliminar': function (e, value, row, index) {
        deleteIDSolicitud(row.codigoSolicitud);
    }
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
            console.log("resultado",resultado)
            if(resultado.flag==1){
                enlistarSolicitudes(0);
            }
        }
    });
}
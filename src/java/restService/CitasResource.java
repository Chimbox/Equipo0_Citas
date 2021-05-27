/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import equipo0_dominio.Cita;
import equipo0_dominio.SolicitudExpediente;
import equipo0_dominio.TrabajadorSalud;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.POST;
import negocios.FactoryNegocios;
import negocios.INegociosAuthPac;
import negocios.INegociosCitas;

/**
 * REST Web Service
 *
 * @author Alfonso Felix
 */
@Path("citas")
public class CitasResource {

    @Context
    private UriInfo context;
    private final INegociosCitas negocios;

    /**
     * Creates a new instance of CitasResource
     */
    public CitasResource() {
        negocios=FactoryNegocios.getFachadaCitas();
    }

    @POST
    @Path("solicitarExpediente")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postSolicitudExpediente(String json) {
        try {
            Gson gson = new GsonBuilder().create();
            SolicitudExpediente solicitud = gson.fromJson(json, SolicitudExpediente.class);
            return negocios.enviarNotificacionPaciente(solicitud);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "{\"estado\":1}";
    }

    @POST
    @Path("actualizarSolicitud")
    @Consumes(MediaType.APPLICATION_JSON)
    public void postActualizarSolicitud(String json) {
        Gson gson=new GsonBuilder().create();
        int idCita=gson.fromJson(json, JsonObject.class).get("idCita").getAsInt();
        negocios.aprobarExpedienteCita(idCita);
    }

    @POST
    @Path("consultarcitas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postConsultarCitas(String trabSaludJson) {
        
        Gson gson=new GsonBuilder().create();
        
        TrabajadorSalud trab = gson.fromJson(trabSaludJson, TrabajadorSalud.class);
        
        List<Cita> citas=negocios.obtenerCitas(trab.getId());
       
        return gson.toJson(citas);
    }
    
    @POST
    @Path("consultaraprobacion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postConsultarAprobacion(String citaJson) {
        
        Gson gson=new GsonBuilder().create();
        
        Cita cita = gson.fromJson(citaJson, Cita.class);
        
        if(negocios.consultaAprobacionExpediente(cita.getId())){
            return "{\"estado\":1}";
        }
       
        return "{\"estado\":0}";
    }
}

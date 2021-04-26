/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import equipo0_dominio.SolicitudExpediente;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.POST;
import negocios.FactoryNegocios;
import negocios.INegocios;

/**
 * REST Web Service
 *
 * @author Alfonso Felix
 */
@Path("citas")
public class CitasResource {

    @Context
    private UriInfo context;
    private INegocios negocios;

    /**
     * Creates a new instance of CitasResource
     */
    public CitasResource() {
        negocios=FactoryNegocios.getFachada();
    }

    @POST
    @Path("solicitarExpediente")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postSolicitudExpediente(String json) {
        try {
            Gson gson = new GsonBuilder().create();
            SolicitudExpediente solicitud = gson.fromJson(json, SolicitudExpediente.class);
            System.out.println("llega a citas: "+ json);
            String tokenPaciente = "f9xib6QCT_ivFxI75dVH9X:APA91bFm0Jd7hzyDP_Of2LE4V3qjUJu3dF5I7QO_N8guZ4726nAC9mnAMKUfFAXbYKdrIGUPk3faOdpyLotTq-zon47xG-lV68U0RhruUrfnRtaFeUs7breUNJrl-Ct3mbcX5G9Ie1G_";
            return negocios.enviarNotificacionPaciente(tokenPaciente, solicitud.getIdCita(), solicitud.getNombreSolicitante(), solicitud.getFechaHora());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "{\"estado\":1}";
    }

    @POST
    @Path("actualizarSolicitud")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postActualizarSolicitud(String content) {
        return content;
    }

    /**
     * PUT method for updating or creating an instance of CitasResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}

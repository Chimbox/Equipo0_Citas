/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import pojo.FirebaseCloudMessage;
import pojo.Message;
import pojo.Notification;

/**
 * REST Web Service
 *
 * @author Alfonso Felix
 */
@Path("citas")
public class CitasResource {

    @Context
    private UriInfo context;


    /**
     * Creates a new instance of CitasResource
     */
    public CitasResource() {

    }

    /**
     * Retrieves representation of an instance of restService.CitasResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {

        String json = "{"
                + "\"conectado\":\"citasResource\""
                + "}";

        enviarNotificacion("f9xib6QCT_ivFxI75dVH9X:APA91bFm0Jd7hzyDP_Of2LE4V3qjUJu3dF5I7QO_N8guZ4726nAC9mnAMKUfFAXbYKdrIGUPk3faOdpyLotTq-zon47xG-lV68U0RhruUrfnRtaFeUs7breUNJrl-Ct3mbcX5G9Ie1G_");
        
        return json;
    }

    public String enviarNotificacion(String token) {
        Gson yison = new GsonBuilder().create();

        CloseableHttpClient httpclient = HttpClients.createDefault();//https://fcm.googleapis.com/fcm/send
        HttpPost post = new HttpPost("https://fcm.googleapis.com/fcm/send");
        StringEntity json;
        try {
            String str = "{\"notification\": {\n"
                    + "  \"title\" : \"Solicitud\",\n"
                    + "  \"body\" : \"Un medico esta solicitando acceso a tu expediente.\"\n"
                    + "  },\n"
                    + "  \"to\" : \""+token+"\",\n"
                    + "  \"direct_boot_ok\" : true}";
            System.out.println(str);
            json = new StringEntity(str);
            //System.out.println(yison.toJson(fn));
            json.setContentType("application/json");

            post.addHeader("Content-Type", "application/json; charset=utf-8");
            post.addHeader("Authorization", "key=AAAAYwqwFm8:APA91bGA3ZzTNP7Cr2STjn9r6u4q6ut3DOGXpm07NZ8y0RvCb_1MuPKxEAJQdR5o7IASGIxb3fSoDXwC3_nm4o_wqgaa06_ilClvGGuhWs78_V1KL8Gxs5X-zac7qo9WFwsxGYeVCyOl");
            
            
            post.setEntity(json);
            CloseableHttpResponse response = httpclient.execute(post);

            String s = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8.name());

            System.out.println(s);

            //notificaciones.postJson(yison.toJson(fn));
        } catch (UnsupportedEncodingException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return "";
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

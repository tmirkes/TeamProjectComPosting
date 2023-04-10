package resource;

import com.google.gson.Gson;
import entity.*;
import persistence.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/servicesV1/")
public class servicesV1 {
    private GenericDao<Service> comPoster = new GenericDao(Service.class);
    private Gson gson = new Gson();
    private String returnData = "";
    private int counter = 0;

    @GET
    @Produces
    public Response getRequestAllPlainText() {
        returnData = "";
        counter = 0;
        List<Service> allServices = new ArrayList(comPoster.getAll());
        while (counter < allServices.size()) {
            listToString(allServices.get(counter));
            counter++;
        }
        return Response.status(200).entity(returnData).build();
    }
    @GET
    @Produces({"text/html"})
    public Response getRequestAllHtml() {
        returnData = "";
        counter = 0;
        List<Service> allServices = new ArrayList(comPoster.getAll());
        while (counter < allServices.size()) {
            listToHtml(allServices.get(counter));
            counter++;
        }
        return Response.status(200).entity(returnData).build();
    }
    @GET
    @Produces({"application/json"})
    public Response getRequestAllJson() {
        returnData = "";
        counter = 0;
        List<Service> allServices = new ArrayList(comPoster.getAll());
        while (counter < allServices.size()) {
            listToString(allServices.get(counter));
            counter++;
        }
        String formattedData = this.gson.toJson(returnData);
        return Response.status(200).entity(formattedData).build();
    }
    public void listToString(Service thisService) {
        returnData += thisService.toString();
    }
    public void listToHtml(Service thisService) {
        returnData += "<p>" + thisService.toString() + "</p>";
    }
}

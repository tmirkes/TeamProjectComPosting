package resource;

import com.google.gson.Gson;
import entity.*;
import persistence.*;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Root resource for <code>Service</code> classes, providing <code>GET</code> method handling for the return of result lists via the <code>Response</code>
 * object.
 *
 * @Author tlmirkes
 * @Version 1.0
 */
@Path("/servicesV1/")
public class servicesV1 {
    private GenericDao<Service> comPoster = new GenericDao(Service.class);
    private Gson gson = new Gson();
    private String returnData = "";
    private int counter = 0;

    /**
     * Accepts <code>GET</code> requests, returning a <code>text/plain</code> entity within a <code>Response</code> object
     * containing an appropriate response code.
     *
     * @return Response object
     */
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

    /**
     * Accepts <code>GET</code> requests, returning a <code>text/html</code> entity within a <code>Response</code> object
     * containing an appropriate response code.
     *
     * @return Response object
     */
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

    /**
     * Accepts <code>GET</code> requests, returning a <code>application/json</code> entity within a <code>Response</code> object
     * containing an appropriate response code.
     *
     * @return Response object
     */
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

    /**
     * Aggregates <code>String</code> output from the passed <code>Service</code> object for use in constructing the
     * <code>Response</code> object attributes.
     *
     * @param thisService Service object in <code>ResultSet</code> data
     */
    public void listToString(Service thisService) {
        returnData += thisService.toString();
    }

    /**
     * Aggregates <code>String</code> output to construct <code>HTML</code> from the passed <code>Service</code> object for use
     * in constructing the <code>Response</code> object attributes.
     *
     * @param thisService Service object in <code>ResultSet</code> data
     */
    public void listToHtml(Service thisService) {
        returnData += "<p>" + thisService.toString() + "</p>";
    }
}

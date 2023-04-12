package resource;

import com.google.gson.Gson;
import entity.*;
import persistence.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Root resource for <code>Service</code> classes, providing <code>GET</code> by ID/<code>PUT</code>/<code>DELETE</code> method handling for the return of result lists via the
 * <code>Response</code> object.
 *
 * @Author tlmirkes
 * @Version 1.0
 */
@Path("/servicesV2/")
public class servicesV2 {
    private GenericDao<Service> comPoster = new GenericDao(Service.class);
    private Gson gson = new Gson();

    /**
     * Accepts <code>GET</code> requests, extracts an ID value to search, and returns a <code>text/plain</code> entity within
     * a <code>Response</code> object containing an appropriate response code.
     *
     * @param id Record entity ID to retrieve
     * @return Response object
     */
    @GET
    @Path("{id: [0-9]*}")
    public Response getRequestPlainText(@PathParam("id") int id) {
        String serviceData = comPoster.getById(id).toString();
        return Response.status(200).entity(serviceData).build();
    }

    /**
     * Accepts <code>GET</code> requests, extracts an ID value to search, and returns a <code>text/html</code> entity within
     * a <code>Response</code> object containing an appropriate response code.
     *
     * @param id Record entity ID to retrieve
     * @return Response object
     */
    @GET
    @Path("{id: [0-9]*}")
    @Produces({"text/html"})
    public Response getRequestHtml(@PathParam("id") int id) {
        Service serviceData = comPoster.getById(id);
        String returnHtml = "<h3>Service:</h3><br><p>" + serviceData.getName() + "</p><p>" + serviceData.getDescription() + "</p>";
        return Response.status(200).entity(returnHtml).build();
    }

    /**
     * Accepts <code>GET</code> requests, extracts an ID value to search, and returns a <code>application/json</code> entity within
     * a <code>Response</code> object containing an appropriate response code.
     *
     * @param id Record entity ID to retrieve
     * @return Response object
     */
    @GET
    @Path("{id: [0-9]*}")
    @Produces({"application/json"})
    public Response getRequestJson(@PathParam("id") int id) {
        Service serviceData = comPoster.getById(id);
        String formattedData = this.gson.toJson(serviceData);
        return Response.status(200).entity(formattedData).build();
    }

    @PUT
    @Path("/alter/{id: [0-9]*}/{name}/{desc}")
    @Consumes("application/x-www-form-urlencoded")
    public Response putRequest(@PathParam("id") int id, @PathParam("name") String name, @PathParam("desc") String desc) {
        Service serviceToEdit = comPoster.getById(id);
        serviceToEdit.setName(name);
        serviceToEdit.setDescription(desc);
        comPoster.editEntity(serviceToEdit);
        return Response.status(200).entity(serviceToEdit.toString()).build();
    }/**

    @DELETE
    @Path("/drop/{id: [0-9]*}")
    public Response deleteRequest(@PathParam("id") int id){
        Service serviceToDelete = comPoster.getById(id);
        comPoster.deleteEntity(serviceToDelete);
        if (comPoster.getById(id) != null) {
            return Response.status(200).entity("Record " + id + " deleted.").build();
        } else {
            return Response.status(404).entity("No such record found.").build();
        }
    }**/
}

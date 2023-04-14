package resource;

import com.google.gson.Gson;
import entity.*;
import persistence.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

@Path("/servicesV2/")
public class servicesV2 {
    private GenericDao<Service> comPoster = new GenericDao(Service.class);
    private Gson gson = new Gson();

    @GET
    @Path("{id: [0-9]*}")
    //@Produces("{text/plain}")
    public Response getRequestPlainText(@PathParam("id") int id) {
        String serviceData = comPoster.getById(id).toString();
        return Response.status(200).entity(serviceData).build();
    }
    @GET
    @Path("{id: [0-9]*}")
    @Produces({"text/html"})
    public Response getRequestHtml(@PathParam("id") int id) {
        Service serviceData = comPoster.getById(id);
        String returnHtml = "<h3>Service:</h3><br><p>" + serviceData.getName() + "</p><p>" + serviceData.getDescription() + "</p>";
        return Response.status(200).entity(returnHtml).build();
    }
    @GET
    @Path("{id: [0-9]*}")
    @Produces({"application/json"})
    public Response getRequestJson(@PathParam("id") int id) {
        Service serviceData = comPoster.getById(id);
        String formattedData = this.gson.toJson(serviceData);
        return Response.status(200).entity(formattedData).build();
    }/**
    @PUT
    @Path("/alter/{id: [0-9]*}")
    @Consumes("application/x-www-form-urlencoded")
    public Response putRequest(MultivaluedMap<String, String> formParameters) {
        int idToEdit = Integer.valueOf(formParameters.get("id").get(0));
        Service serviceToEdit = comPoster.getById(idToEdit);
        serviceToEdit.setName(formParameters.get("name").get(0));
        serviceToEdit.setDescription(formParameters.get("description").get(0));
        comPoster.editEntity(serviceToEdit);
        return Response.status(200).entity(serviceToEdit.toString()).build();
    }
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

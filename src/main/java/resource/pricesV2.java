package resource;

import com.google.gson.Gson;
import entity.Price;
import persistence.GenericDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/pricesV2/")
public class pricesV2 {
    private GenericDao<Price> comPoster = new GenericDao(Price.class);
    private Gson gson = new Gson();

    @GET
    @Path("{id: [0-9]*}")
    //@Produces("{text/plain}")
    public Response getRequestPlainText(@PathParam("id") int id) {
        String priceData = comPoster.getById(id).toString();
        return Response.status(200).entity(priceData).build();
    }
    @GET
    @Path("{id: [0-9]*}")
    @Produces({"text/html"})
    public Response getRequestHtml(@PathParam("id") int id) {
        Price priceData = comPoster.getById(id);
        String returnHtml = "<h3>Price:</h3><br><p>" + priceData.getPerUnit() + "</p><p>" + priceData.getUnitType() + "</p>";
        return Response.status(200).entity(returnHtml).build();
    }
    @GET
    @Path("{id: [0-9]*}")
    @Produces({"application/json"})
    public Response getRequestJson(@PathParam("id") int id) {
        Price priceData = comPoster.getById(id);
        String formattedData = this.gson.toJson(priceData);
        return Response.status(200).entity(formattedData).build();
    }/**
    @PUT
    @Path("/alter/{id: [0-9]*}")
    @Consumes("application/x-www-form-urlencoded")
    public Response putRequest(MultivaluedMap<String, String> formParameters) {
        int idToEdit = Integer.valueOf(formParameters.get("id").get(0));
        Price priceToEdit = comPoster.getById(idToEdit);
        priceToEdit.setName(formParameters.get("name").get(0));
        priceToEdit.setDescription(formParameters.get("description").get(0));
        comPoster.editEntity(priceToEdit);
        return Response.status(200).entity(priceToEdit.toString()).build();
    }
    @DELETE
    @Path("/drop/{id: [0-9]*}")
    public Response deleteRequest(@PathParam("id") int id){
        Price priceToDelete = comPoster.getById(id);
        comPoster.deleteEntity(priceToDelete);
        if (comPoster.getById(id) != null) {
            return Response.status(200).entity("Record " + id + " deleted.").build();
        } else {
            return Response.status(404).entity("No such record found.").build();
        }
    }**/
}

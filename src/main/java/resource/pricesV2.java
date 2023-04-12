package resource;

import com.google.gson.Gson;
import entity.Price;
import persistence.GenericDao;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

/**
 * Root resource for <code>Price</code> classes, providing <code>GET</code> by ID/<code>PUT</code>/<code>DELETE</code> method handling for the return of result lists via the
 * <code>Response</code> object.
 *
 * @Author tlmirkes
 * @Version 1.0
 */
@Path("/pricesV2/")
public class pricesV2 {
    private GenericDao<Price> comPoster = new GenericDao(Price.class);
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
    //@Produces("{text/plain}")
    public Response getRequestPlainText(@PathParam("id") int id) {
        String priceData = comPoster.getById(id).toString();
        return Response.status(200).entity(priceData).build();
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
        Price priceData = comPoster.getById(id);
        String returnHtml = "<h3>Price:</h3><br><p>" + priceData.getPerUnit() + "</p><p>" + priceData.getUnitType() + "</p>";
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

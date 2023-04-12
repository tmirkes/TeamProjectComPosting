package resource;

import com.google.gson.Gson;
import entity.Period;
import persistence.GenericDao;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

/**
 * Root resource for <code>Period</code> classes, providing <code>GET</code> by ID/<code>PUT</code>/<code>DELETE</code> method handling for the return of result lists via the
 * <code>Response</code> object.
 *
 * @Author tlmirkes
 * @Version 1.0
 */
@Path("/periodsV2/")
public class periodsV2 {
    private GenericDao<Period> comPoster = new GenericDao(Period.class);
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
        String periodData = comPoster.getById(id).toString();
        return Response.status(200).entity(periodData).build();
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
        Period periodData = comPoster.getById(id);
        String returnHtml = "<h3>Period:</h3><br><p>" + periodData.getFrequency() + "</p><p>" + periodData.getTimeUnit() + "</p>";
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
        Period periodData = comPoster.getById(id);
        String formattedData = this.gson.toJson(periodData);
        return Response.status(200).entity(formattedData).build();
    }

    /**
    @PUT
    @Path("/alter/{id: [0-9]*}")
    @Consumes("application/x-www-form-urlencoded")
    public Response putRequest(MultivaluedMap<String, String> formParameters) {
        int idToEdit = Integer.valueOf(formParameters.get("id").get(0));
        Period periodToEdit = comPoster.getById(idToEdit);
        periodToEdit.setName(formParameters.get("name").get(0));
        periodToEdit.setDescription(formParameters.get("description").get(0));
        comPoster.editEntity(periodToEdit);
        return Response.status(200).entity(periodToEdit.toString()).build();
    }
    @DELETE
    @Path("/drop/{id: [0-9]*}")
    public Response deleteRequest(@PathParam("id") int id){
        Period periodToDelete = comPoster.getById(id);
        comPoster.deleteEntity(periodToDelete);
        if (comPoster.getById(id) != null) {
            return Response.status(200).entity("Record " + id + " deleted.").build();
        } else {
            return Response.status(404).entity("No such record found.").build();
        }
    }**/
}

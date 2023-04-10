package resource;

import com.google.gson.Gson;
import entity.Period;
import persistence.GenericDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/periodsV2/")
public class periodsV2 {
    private GenericDao<Period> comPoster = new GenericDao(Period.class);
    private Gson gson = new Gson();

    @GET
    @Path("{id: [0-9]*}")
    //@Produces("{text/plain}")
    public Response getRequestPlainText(@PathParam("id") int id) {
        String periodData = comPoster.getById(id).toString();
        return Response.status(200).entity(periodData).build();
    }
    @GET
    @Path("{id: [0-9]*}")
    @Produces({"text/html"})
    public Response getRequestHtml(@PathParam("id") int id) {
        Period periodData = comPoster.getById(id);
        String returnHtml = "<h3>Period:</h3><br><p>" + periodData.getFrequency() + "</p><p>" + periodData.getTimeUnit() + "</p>";
        return Response.status(200).entity(returnHtml).build();
    }
    @GET
    @Path("{id: [0-9]*}")
    @Produces({"application/json"})
    public Response getRequestJson(@PathParam("id") int id) {
        Period periodData = comPoster.getById(id);
        String formattedData = this.gson.toJson(periodData);
        return Response.status(200).entity(formattedData).build();
    }/**
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

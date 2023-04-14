package resource;

import com.google.gson.Gson;
import entity.Material;
import persistence.GenericDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/materialsV2/")
public class materialsV2 {
    private GenericDao<Material> comPoster = new GenericDao(Material.class);
    private Gson gson = new Gson();

    @GET
    @Path("{id: [0-9]*}")
    //@Produces("{text/plain}")
    public Response getRequestPlainText(@PathParam("id") int id) {
        String materialData = comPoster.getById(id).toString();
        return Response.status(200).entity(materialData).build();
    }
    @GET
    @Path("{id: [0-9]*}")
    @Produces({"text/html"})
    public Response getRequestHtml(@PathParam("id") int id) {
        Material materialData = comPoster.getById(id);
        String returnHtml = "<h3>Material:</h3><br><p>" + materialData.getName() + "</p><p>" + materialData.getComments() + "</p>";
        return Response.status(200).entity(returnHtml).build();
    }
    @GET
    @Path("{id: [0-9]*}")
    @Produces({"application/json"})
    public Response getRequestJson(@PathParam("id") int id) {
        Material materialData = comPoster.getById(id);
        String formattedData = this.gson.toJson(materialData);
        return Response.status(200).entity(formattedData).build();
    }/**
    @PUT
    @Path("/alter/{id: [0-9]*}")
    @Consumes("application/x-www-form-urlencoded")
    public Response putRequest(MultivaluedMap<String, String> formParameters) {
        int idToEdit = Integer.valueOf(formParameters.get("id").get(0));
        Material materialToEdit = comPoster.getById(idToEdit);
        materialToEdit.setName(formParameters.get("name").get(0));
        materialToEdit.setDescription(formParameters.get("description").get(0));
        comPoster.editEntity(materialToEdit);
        return Response.status(200).entity(materialToEdit.toString()).build();
    }
    @DELETE
    @Path("/drop/{id: [0-9]*}")
    public Response deleteRequest(@PathParam("id") int id){
        Material materialToDelete = comPoster.getById(id);
        comPoster.deleteEntity(materialToDelete);
        if (comPoster.getById(id) != null) {
            return Response.status(200).entity("Record " + id + " deleted.").build();
        } else {
            return Response.status(404).entity("No such record found.").build();
        }
    }**/
}

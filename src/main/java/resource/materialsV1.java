package resource;

import com.google.gson.Gson;
import entity.Material;
import persistence.GenericDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/materialsV1/")
public class materialsV1 {
    private GenericDao<Material> comPoster = new GenericDao(Material.class);
    private Gson gson = new Gson();
    private String returnData = "";
    private int counter = 0;

    @GET
    @Produces
    public Response getRequestAllPlainText() {
        returnData = "";
        counter = 0;
        List<Material> allMaterials = new ArrayList(comPoster.getAll());
        while (counter < allMaterials.size()) {
            listToString(allMaterials.get(counter));
            counter++;
        }
        return Response.status(200).entity(returnData).build();
    }
    @GET
    @Produces({"text/html"})
    public Response getRequestAllHtml() {
        returnData = "";
        counter = 0;
        List<Material> allMaterials = new ArrayList(comPoster.getAll());
        while (counter < allMaterials.size()) {
            listToHtml(allMaterials.get(counter));
            counter++;
        }
        return Response.status(200).entity(returnData).build();
    }
    @GET
    @Produces({"application/json"})
    public Response getRequestAllJson() {
        returnData = "";
        counter = 0;
        List<Material> allMaterials = new ArrayList(comPoster.getAll());
        while (counter < allMaterials.size()) {
            listToString(allMaterials.get(counter));
            counter++;
        }
        String formattedData = this.gson.toJson(returnData);
        return Response.status(200).entity(formattedData).build();
    }
    public void listToString(Material thisMaterial) {
        returnData += thisMaterial.toString();
    }
    public void listToHtml(Material thisMaterial) {
        returnData += "<p>" + thisMaterial.toString() + "</p>";
    }
}

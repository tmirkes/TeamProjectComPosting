package resource;

import com.google.gson.Gson;
import entity.Material;
import persistence.GenericDao;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Root resource for <code>Material</code> classes, providing <code>GET</code> method handling for the return of result lists via the <code>Response</code>
 * object.
 *
 * @Author tlmirkes
 * @Version 1.0
 */
@Path("/materialsV1/")
public class materialsV1 {
    private GenericDao<Material> comPoster = new GenericDao(Material.class);
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
        List<Material> allMaterials = new ArrayList(comPoster.getAll());
        while (counter < allMaterials.size()) {
            listToHtml(allMaterials.get(counter));
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
        List<Material> allMaterials = new ArrayList(comPoster.getAll());
        while (counter < allMaterials.size()) {
            listToString(allMaterials.get(counter));
            counter++;
        }
        String formattedData = this.gson.toJson(returnData);
        return Response.status(200).entity(formattedData).build();
    }

    /**
     * Aggregates <code>String</code> output from the passed <code>Material</code> object for use in constructing the
     * <code>Response</code> object attributes.
     *
     * @param thisMaterial Material object in <code>ResultSet</code> data
     */
    public void listToString(Material thisMaterial) {
        returnData += thisMaterial.toString();
    }

    /**
     * Aggregates <code>String</code> output to construct <code>HTML</code> from the passed <code>Material</code> object for use
     * in constructing the <code>Response</code> object attributes.
     *
     * @param thisMaterial Material object in <code>ResultSet</code> data
     */
    public void listToHtml(Material thisMaterial) {
        returnData += "<p>" + thisMaterial.toString() + "</p>";
    }
}

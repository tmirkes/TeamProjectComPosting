package resource;

import com.google.gson.Gson;
import entity.Material;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import persistence.GenericDao;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/materialsV3/")
public class materialsV3 {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao<Material> comPoster = new GenericDao(Material.class);
    private Gson gson = new Gson();

    @POST
    @Path("/{name}/{description}")
    @Consumes("application/x-www-form-urlencoded")
    public Response postRequest(@PathParam("name") String name, @PathParam("description") String desc) {
        int newId = comPoster.addEntity(new Material(name, desc));
        Material insertedMaterial = comPoster.getById(newId);
        return Response.status(200).entity(insertedMaterial.toString()).build();
    }
}

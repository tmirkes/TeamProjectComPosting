package resource;

import com.google.gson.Gson;
import entity.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import persistence.GenericDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

@Path("/servicesV3/")
public class servicesV3 {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao<Service> comPoster = new GenericDao(Service.class);
    private Gson gson = new Gson();

    @POST
    @Path("/{name}/{description}")
    @Consumes("application/x-www-form-urlencoded")
    public Response postRequest(@PathParam("name") String name, @PathParam("description") String desc) {
        int newId = comPoster.addEntity(new Service(name, desc));
        Service insertedService = comPoster.getById(newId);
        return Response.status(200).entity(insertedService.toString()).build();
    }
}

package resource;

import com.google.gson.Gson;
import entity.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import persistence.GenericDao;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Root resource for <code>Service</code> classes, providing <code>POST</code> method handling for the insertion of new records
 * via the while returning a code via a <code>Response</code> object.
 *
 * @Author tlmirkes
 * @Version 1.0
 */
@Path("/servicesV3/")
public class servicesV3 {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao<Service> comPoster = new GenericDao(Service.class);
    private Gson gson = new Gson();

    /**
     * Accepts <code>POST</code> requests, extracts entity parameters from the URI, and generates a new persistence entity.
     * An appropriate response code is returned on persistence completion.
     *
     * @param name Name of the Material record
     * @param desc Description of the Material record
     * @return Response object
     */
    @POST
    @Path("/{name}/{description}")
    @Consumes("application/x-www-form-urlencoded")
    public Response postRequest(@PathParam("name") String name, @PathParam("description") String desc) {
        int newId = comPoster.addEntity(new Service(name, desc));
        Service insertedService = comPoster.getById(newId);
        return Response.status(200).entity(insertedService.toString()).build();
    }
}

package resource;

import com.google.gson.Gson;
import entity.Price;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import persistence.GenericDao;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

/**
 * Root resource for <code>Price</code> classes, providing <code>POST</code> method handling for the insertion of new records
 * via the while returning a code via a <code>Response</code> object.
 *
 * @Author tlmirkes
 * @Version 1.0
 */
@Path("/pricesV3/")
public class pricesV3 {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao<Price> comPoster = new GenericDao(Price.class);
    private Gson gson = new Gson();

    /**
     * Accepts <code>POST</code> requests, extracts entity parameters from the URI, and generates a new persistence entity.
     * An appropriate response code is returned on persistence completion.
     *
     * @param price Numerical value of the Price record
     * @param unit Monetary unit of the Price record
     * @return Response object
     */
    @POST
    @Path("/{price}/{unit}")
    @Consumes("application/x-www-form-urlencoded")
    public Response postRequest(@PathParam("price") Double price, @PathParam("unit") String unit) {
        int newId = comPoster.addEntity(new Price(price, unit));
        Price insertedPrice = comPoster.getById(newId);
        return Response.status(200).entity(insertedPrice.toString()).build();
    }
}

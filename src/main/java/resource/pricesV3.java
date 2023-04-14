package resource;

import com.google.gson.Gson;
import entity.Price;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import persistence.GenericDao;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/pricesV3/")
public class pricesV3 {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao<Price> comPoster = new GenericDao(Price.class);
    private Gson gson = new Gson();

    @POST
    @Path("/{price}/{unit}")
    @Consumes("application/x-www-form-urlencoded")
    public Response postRequest(@PathParam("price") Double price, @PathParam("unit") String unit) {
        int newId = comPoster.addEntity(new Price(price, unit));
        Price insertedPrice = comPoster.getById(newId);
        return Response.status(200).entity(insertedPrice.toString()).build();
    }
}

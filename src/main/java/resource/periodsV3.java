package resource;

import com.google.gson.Gson;
import entity.Period;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import persistence.GenericDao;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/periodsV3/")
public class periodsV3 {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao<Period> comPoster = new GenericDao(Period.class);
    private Gson gson = new Gson();

    @POST
    @Path("/{frequency}/{time_unit}")
    @Consumes("application/x-www-form-urlencoded")
    public Response postRequest(@PathParam("frequency") int frequency, @PathParam("time_unit") String time_unit) {
        int newId = comPoster.addEntity(new Period(frequency, time_unit));
        Period insertedPeriod = comPoster.getById(newId);
        return Response.status(200).entity(insertedPeriod.toString()).build();
    }
}

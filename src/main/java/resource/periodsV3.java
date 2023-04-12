package resource;

import com.google.gson.Gson;
import entity.Period;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import persistence.GenericDao;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

/**
 * Root resource for <code>Period</code> classes, providing <code>POST</code> method handling for the insertion of new records
 * via the while returning a code via a <code>Response</code> object.
 *
 * @Author tlmirkes
 * @Version 1.0
 */
@Path("/periodsV3/")
public class periodsV3 {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao<Period> comPoster = new GenericDao(Period.class);
    private Gson gson = new Gson();

    /**
     * Accepts <code>POST</code> requests, extracts entity parameters from the URI, and generates a new persistence entity.
     * An appropriate response code is returned on persistence completion.
     *
     * @param frequency Frequency of the Period record
     * @param time_unit Time unit of the Period record
     * @return Response object
     */
    @POST
    @Path("/{frequency}/{time_unit}")
    @Consumes("application/x-www-form-urlencoded")
    public Response postRequest(@PathParam("frequency") int frequency, @PathParam("time_unit") String time_unit) {
        int newId = comPoster.addEntity(new Period(frequency, time_unit));
        Period insertedPeriod = comPoster.getById(newId);
        return Response.status(200).entity(insertedPeriod.toString()).build();
    }
}

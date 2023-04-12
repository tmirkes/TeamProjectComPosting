package resource;

import com.google.gson.Gson;
import entity.Period;
import persistence.GenericDao;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Root resource for <code>Period</code> classes, providing <code>GET</code> method handling for the return of result lists via the <code>Response</code>
 * object.
 *
 * @Author tlmirkes
 * @Version 1.0
 */
@Path("/periodsV1/")
public class periodsV1 {
    private GenericDao<Period> comPoster = new GenericDao(Period.class);
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
    @Produces
    public Response getRequestAllPlainText() {
        returnData = "";
        counter = 0;
        List<Period> allPeriods = new ArrayList(comPoster.getAll());
        while (counter < allPeriods.size()) {
            listToString(allPeriods.get(counter));
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
        List<Period> allPeriods = new ArrayList(comPoster.getAll());
        while (counter < allPeriods.size()) {
            listToHtml(allPeriods.get(counter));
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
        List<Period> allPeriods = new ArrayList(comPoster.getAll());
        while (counter < allPeriods.size()) {
            listToString(allPeriods.get(counter));
            counter++;
        }
        String formattedData = this.gson.toJson(returnData);
        return Response.status(200).entity(formattedData).build();
    }

    /**
     * Aggregates <code>String</code> output from the passed <code>Period</code> object for use in constructing the
     * <code>Response</code> object attributes.
     *
     * @param thisPeriod Period object in <code>ResultSet</code> data
     */
    public void listToString(Period thisPeriod) {
        returnData += thisPeriod.toString();
    }

    /**
     * Aggregates <code>String</code> output to construct <code>HTML</code> from the passed <code>Period</code> object for use
     * in constructing the <code>Response</code> object attributes.
     *
     * @param thisPeriod Period object in <code>ResultSet</code> data
     */
    public void listToHtml(Period thisPeriod) {
        returnData += "<p>" + thisPeriod.toString() + "</p>";
    }
}

package resource;

import com.google.gson.Gson;
import entity.Period;
import persistence.GenericDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/periodsV1/")
public class periodsV1 {
    private GenericDao<Period> comPoster = new GenericDao(Period.class);
    private Gson gson = new Gson();
    private String returnData = "";
    private int counter = 0;

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
    public void listToString(Period thisPeriod) {
        returnData += thisPeriod.toString();
    }
    public void listToHtml(Period thisPeriod) {
        returnData += "<p>" + thisPeriod.toString() + "</p>";
    }
}

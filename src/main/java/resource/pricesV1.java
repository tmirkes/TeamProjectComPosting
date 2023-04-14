package resource;

import com.google.gson.Gson;
import entity.Price;
import persistence.GenericDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/pricesV1/")
public class pricesV1 {
    private GenericDao<Price> comPoster = new GenericDao(Price.class);
    private Gson gson = new Gson();
    private String returnData = "";
    private int counter = 0;

    @GET
    @Produces
    public Response getRequestAllPlainText() {
        returnData = "";
        counter = 0;
        List<Price> allPrices = new ArrayList(comPoster.getAll());
        while (counter < allPrices.size()) {
            listToString(allPrices.get(counter));
            counter++;
        }
        return Response.status(200).entity(returnData).build();
    }
    @GET
    @Produces({"text/html"})
    public Response getRequestAllHtml() {
        returnData = "";
        counter = 0;
        List<Price> allPrices = new ArrayList(comPoster.getAll());
        while (counter < allPrices.size()) {
            listToHtml(allPrices.get(counter));
            counter++;
        }
        return Response.status(200).entity(returnData).build();
    }
    @GET
    @Produces({"application/json"})
    public Response getRequestAllJson() {
        returnData = "";
        counter = 0;
        List<Price> allPrices = new ArrayList(comPoster.getAll());
        while (counter < allPrices.size()) {
            listToString(allPrices.get(counter));
            counter++;
        }
        String formattedData = this.gson.toJson(returnData);
        return Response.status(200).entity(formattedData).build();
    }
    public void listToString(Price thisPrice) {
        returnData += thisPrice.toString();
    }
    public void listToHtml(Price thisPrice) {
        returnData += "<p>" + thisPrice.toString() + "</p>";
    }
}

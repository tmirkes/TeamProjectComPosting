package controller;

import entity.*;
import persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Accepts form data from JSP inputs, parsing and filtering to pass data to resources.
 *
 * @Author tlmirkes
 * @Version 1.0
 */
@WebServlet(name = "SearchManager", urlPatterns = {"/search"})
public class SearchManager extends HttpServlet {
    private final String PERIOD_SEARCH = "period";
    private final String MATERIAL_SEARCH = "material";
    private final String SERVICE_SEARCH = "service";
    private final String PRICE_SEARCH = "price";
    private GenericDao<Object> searchDao;
    private ArrayList<Object> results;
    private int searchItem;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Handles HTTP GET requests.
     *
     * @param request the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @exception ServletException if there is a Servlet failure
     * @exception IOException if there is an IO failure
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("doGet entered");
        results = new ArrayList<Object>();
        getSearchObjectType(request);
        if (request.getParameter("submit").equals("viewAll")) {
            getAllResults();
        } else if (request.getParameter("submit").equals("search")) {
            getSearchResult();
        }
        HttpSession session = request.getSession();
        session.setAttribute("results", results);
        session.setAttribute("processed", true);
        String url = "/index.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    /**
     * Forward all doPost requests to the <code>doGet()</code> method.
     *
     * @param request the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @exception ServletException if there is a Servlet failure
     * @exception IOException if there is an IO failure
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Parse form data from the <code>HttpServletRequest</code> object to instantiate the proper <code>GenericDao</code> version
     * and set the search criteria variable.
     *
     * @param request the HttpServletRequest object
     */
    public void getSearchObjectType(HttpServletRequest request) {
        if (request.getParameter("tables").compareTo(PERIOD_SEARCH) == 0) {
            searchDao = new GenericDao(Period.class);
        } else if (request.getParameter("tables").compareTo(MATERIAL_SEARCH) == 0) {
            searchDao = new GenericDao(Material.class);
        } else if (request.getParameter("tables").compareTo(SERVICE_SEARCH) == 0) {
            searchDao = new GenericDao(Service.class);
        } else if (request.getParameter("tables").compareTo(PRICE_SEARCH) == 0) {
            searchDao = new GenericDao(Price.class);
        }
        if (request.getParameter("searchItem").compareTo("") != 0) {
            searchItem = Integer.parseInt(request.getParameter("searchItem"));
        }
    }

    /**
     * DAO access method to retrieve all table records and assign them to the <code>results</code> variable.
     */
    public void getAllResults() {
        results = searchDao.getAll();
    }

    /**
     * DAO access method to retrieve a table record by ID and assign them to the <code>results</code> variable.
     */
    public void getSearchResult() {
        results.add(searchDao.getById(searchItem));
    }

    /**
     * Results getter
     *
     * @return value of results
     */
    public ArrayList<Object> getResults() {
        return results;
    }

    /**
     * Results setter
     *
     * @param results new value of results
     */
    public void setResults(ArrayList<Object> results) {
        this.results = results;
    }

    /**
     * SearchItem getter
     *
     * @return value of searchItem
     */
    public int getSearchItem() {
        return searchItem;
    }

    /**
     * SearchItem setter
     *
     * @param searchItem new value of searchItem
     */
    public void setSearchItem(int searchItem) {
        this.searchItem = searchItem;
    }
}

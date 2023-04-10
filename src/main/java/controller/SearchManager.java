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

@WebServlet(name = "SearchManager", value = "/search")

public class SearchManager extends HttpServlet {
    private final String PERIOD_SEARCH = "period";
    private final String MATERIAL_SEARCH = "material";
    private final String SERVICE_SEARCH = "service";
    private final String PRICE_SEARCH = "price";
    private GenericDao<Object> searchDao;
    private ArrayList<Object> results;
    private int searchItem;
    private final Logger logger = LogManager.getLogger(this.getClass());

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

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

    public void getAllResults() {
        results = searchDao.getAll();
    }

    public void getSearchResult() {
        results.add(searchDao.getById(searchItem));
    }

    public ArrayList<Object> getResults() {
        return results;
    }

    public void setResults(ArrayList<Object> results) {
        this.results = results;
    }

    public int getSearchItem() {
        return searchItem;
    }

    public void setSearchItem(int searchItem) {
        this.searchItem = searchItem;
    }
}

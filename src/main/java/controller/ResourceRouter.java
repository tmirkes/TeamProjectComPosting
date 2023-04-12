package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utility.RequestWrapper;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Accepts form data from JSP inputs, parsing and filtering to pass data to resources.
 *
 * @Author tlmirkes
 * @Version 1.0
 */
@WebServlet(name = "ResourceRouter", urlPatterns = {"/routing"})
public class ResourceRouter extends HttpServlet {
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

        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            logger.info("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
        }

        // HttpRequestWrapper class to update the method parameter of the request
        // Concept initially found here:
        // https://stackoverflow.com/questions/1413129/modify-request-parameter-with-servlet-filter
        // Patterned using Example 3 here:
        // https://www.programcreek.com/java-api-examples/javax.servlet.http.HttpServletRequestWrapper
        RequestWrapper newRequest = new RequestWrapper(request);

        String targetUrl = request.getParameter("targetUrl");

        if (targetUrl.contains("V1")) {
            newRequest.setMethod("GET");
            logger.info("GET - list return");
        } else if (targetUrl.contains("V2") && !targetUrl.contains("alter") && !targetUrl.contains("drop")) {
            newRequest.setMethod("GET");
            logger.info("GET - single entry return");
        } else if (targetUrl.contains("V2") && targetUrl.contains("alter")) {
            newRequest.setMethod("PUT");
            logger.info("PUT - edit single entry");
        } else if (targetUrl.contains("V2") && targetUrl.contains("drop")) {
            newRequest.setMethod("DELETE");
            logger.info("DELETE - drop single entry");
        } else if (targetUrl.contains("V3")) {
            newRequest.setMethod("POST");
            logger.info("POST - add new entry");
        } else {
            newRequest.setMethod("GET");
            logger.info("GET - no valid URL, GET default");
        }

        String url = targetUrl;
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(newRequest, response);
    }

    /**
     * Forward all <code>doPost</code> requests to the <code>doGet</code> method.
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
     * Forward all <code>doPut</code> requests to the <code>doGet</code> method.
     *
     * @param request the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @exception ServletException if there is a Servlet failure
     * @exception IOException if there is an IO failure
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    /**
     * Forward all <code>doDelete</code>> requests to the <code>doGet</code> method.
     *
     * @param request the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @exception ServletException if there is a Servlet failure
     * @exception IOException if there is an IO failure
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

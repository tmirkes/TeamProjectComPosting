package controller;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Handles initial browser requests loading the index.jsp file, redirecting them to the application page after setting
 * a status variable to the <code>Session</code> context.
 *
 * @Author tlmirkes
 * @Version 1.0
 */
@WebServlet(
        name="TrafficControl",
        urlPatterns = { "/redirect" }
)
public class TrafficControl extends HttpServlet {

    /**
     * Handles HTTP GET requests.
     *
     * @param request the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @exception ServletException if there is a Servlet failure
     * @exception IOException if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("processed", false);

        String url = "/index.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
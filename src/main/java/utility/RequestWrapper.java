package utility;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Provides wrapper for <code>HttpServletRequest</code> objects.  Retains original <code>request</code> parameters,
 * adds <code>@Override</code> methods to functionally "set" request parameters.
 *
 * @Author tlmirkes
 * @Version 1.0
 */
public class RequestWrapper extends HttpServletRequestWrapper {
    private String method;

    /**
     * Single-argument constructor
     *
     * @param request HttpServletRequest object to be wrapped
     */
    public RequestWrapper(HttpServletRequest request) {
        super(request);
    }

    /**
     * Method setter.  Validates incoming <code>String</code> before setting; writes default <code>GET</code> method if
     * provided method is invalid.
     *
     * @param newMethod new method value
     */
    public void setMethod(String newMethod) {
        // Initializing technique for HashSet<> found here:
        // https://www.baeldung.com/java-initialize-hashset
        Set<String> validMethods = new HashSet<String>(){{
            add("GET");
            add("POST");
            add("PUT");
            add("DELETE");
        }};
        if (validMethods.contains(newMethod)) {
            this.method = newMethod;
        } else {
            this.method = "GET";
        }
    }

    /**
     * Method getter; <code>overrides</code> wrapped <code>HttpServletRequest</code> object's <code>getMethod()</code> method.
     *
     * @return method value
     */
    @Override
    public String getMethod() {
        return this.method;
    }
}

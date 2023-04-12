package controller;

import resource.*;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Primary application controller for the comPosting API.
 *
 * @Author tlmirkes
 * @Version 1.0
 */
@ApplicationPath("/comPosting/")
public class appConfig extends Application {

    /**
     * Register resource classes in the application and add them to the URI directory.
     *
     * @return Set of Class objects of all resource class types
     */
    @Override
    public Set<Class<?>> getClasses() {
        HashSet functionality = new HashSet<Class<?>>();
        functionality.add(servicesV1.class);
        functionality.add(servicesV2.class);
        functionality.add(servicesV3.class);
        functionality.add(materialsV1.class);
        functionality.add(materialsV2.class);
        functionality.add(materialsV3.class);
        functionality.add(pricesV1.class);
        functionality.add(pricesV2.class);
        functionality.add(pricesV3.class);
        functionality.add(periodsV1.class);
        functionality.add(periodsV2.class);
        functionality.add(periodsV3.class);

        return functionality;
    }
}

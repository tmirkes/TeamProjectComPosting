package controller;

import resource.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/comPosting/")
public class appConfig extends Application {

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

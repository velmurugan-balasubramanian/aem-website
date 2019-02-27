package com.aem.website.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */
@Component(service=Servlet.class,
        property={
                Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
                "sling.servlet.paths="+ "/bin/saveFormData",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET

        })
public class MongoDataWriteServlet extends SlingAllMethodsServlet {

    private static final long serialVersionUid = 1L;

    @Override
    protected void doPost(final SlingHttpServletRequest req,
                         final SlingHttpServletResponse resp) throws ServletException, IOException {
        //final Resource resource = req.getResource();

        Map<String, String[]> bandInfo = req.getParameterMap();
        String bandName = Arrays.asList(bandInfo.get("bandName")).get(0);
        String albumName =  Arrays.asList(bandInfo.get("albumName")).get(0);
        resp.setContentType("text/plain");
        resp.getWriter().write(bandName + albumName);
    }
}

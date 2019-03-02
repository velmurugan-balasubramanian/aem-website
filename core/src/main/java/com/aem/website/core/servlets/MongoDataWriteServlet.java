package com.aem.website.core.servlets;

// Sling Imports
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aem.website.core.services.SaveDataService;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */
@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
		"sling.servlet.paths=" + "/bin/saveFormData", "sling.servlet.methods=" + HttpConstants.METHOD_POST

})
public class MongoDataWriteServlet extends SlingAllMethodsServlet {

	private static final long serialVersionUid = 1L;

	@Reference
	private SaveDataService savedata;

	@Override
	protected void doPost(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
			throws ServletException, IOException {
		
		JSONObject finalResponse = new JSONObject();
		
		// Get request parameters and put into a map 
		Map<String, String[]> userDetails = req.getParameterMap();

		// Invoke the saveDataTOMongoDB with userDetails Map as parameter
		finalResponse = savedata.saveDataTOMongoDB(userDetails);
		
		resp.setContentType("application/json");
		resp.getWriter().print(finalResponse);
	}
}

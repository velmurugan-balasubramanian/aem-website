package com.aem.website.core.impl;

import com.aem.website.core.services.SaveDataService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//Query Builder
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.Query;
import com.day.cq.dam.api.Asset;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.day.cq.search.result.Hit;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.DBCursor;

//Exceptions
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.bson.Document;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(service=SaveDataService.class,immediate = true,
        property={
                Constants.SERVICE_DESCRIPTION + "=Service for Latest Thiniking homepage component"
        })

public class SaveDataServiceimpl implements SaveDataService {

    public JSONObject saveDataTOMongoDB(Map<String, String[]> user) {

        JSONObject jsonResponse = new JSONObject();
        JSONArray arrayJSON = new JSONArray();

        String firstname = Arrays.asList(user.get("firstname")).get(0);
        String lastname =  Arrays.asList(user.get("lastname")).get(0);
        String email =  Arrays.asList(user.get("email")).get(0);
        String enquiry =  Arrays.asList(user.get("enquiry")).get(0);

        try {
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            MongoDatabase db = mongoClient.getDatabase("userDetails");
            MongoCollection<Document> collection = db.getCollection("formData");
            Document doc = new Document();
            doc.append("firstname",firstname);
            doc.append("lastname",lastname);
            doc.append("email",email);
            doc.append("enquiry",enquiry);
            collection.insertOne(doc);
            jsonResponse.put("status","Success");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  jsonResponse;
    }
}

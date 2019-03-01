package com.aem.website.core.impl;

import com.aem.website.core.services.ReadDataService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.bson.Document;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(service=ReadDataService.class,immediate = true,
        property={
                Constants.SERVICE_DESCRIPTION + "=Service for Latest Thiniking homepage component"
        })

public class ReadDataServiceimpl implements ReadDataService {

    public JSONObject readDataFromMongoDB() {

        JSONObject jsonResponse = new JSONObject();
        JSONArray arrayJSON = new JSONArray();

        try {

            MongoClient mongoClient = new MongoClient( "ds143132.mlab.com" , 43132 );
            MongoDatabase db = mongoClient.getDatabase("userDetails");
            MongoCollection<Document> collection = db.getCollection("formData");
            List<Document> documents = (List<Document>) collection.find().into(new ArrayList<Document>());
            for(Document document : documents){
                arrayJSON.put(document.toJson());
            }
            jsonResponse.put("result",arrayJSON);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  jsonResponse;
    }

}

package com.aem.website.core.impl;

import com.aem.website.core.services.ReadDataService;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

// MongoDB imports
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.bson.Document;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = ReadDataService.class, immediate = true, property = {
		Constants.SERVICE_DESCRIPTION + "=Service to Read data from Mongo DB" })

public class ReadDataServiceimpl implements ReadDataService {

	public JSONObject readDataFromMongoDB() {

		JSONObject jsonResponse = new JSONObject();
		JSONArray arrayJSON = new JSONArray();

		try {

			// connect to MongoDB instance
			MongoClient mongoClient = new MongoClient("localhost", 27017);

			// Get DB and collection inside DB, DB and collection will be if not already
			// exist
			MongoDatabase db = mongoClient.getDatabase("userDetails");
			MongoCollection<Document> collection = db.getCollection("formData");

			// Retrieving all documents from the DB and save into a List
			List<Document> documents = (List<Document>) collection.find().into(new ArrayList<Document>());

			// Iterate through list and put into JSON Array
			for (Document document : documents) {
				arrayJSON.put(document.toJson());
			}

			// Put JSON Array into JSON Object
			jsonResponse.put("result", arrayJSON);

			// Close MongoDB client instance
			mongoClient.close();

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonResponse;
	}

}

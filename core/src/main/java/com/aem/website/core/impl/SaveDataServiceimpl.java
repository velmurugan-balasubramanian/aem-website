package com.aem.website.core.impl;

import org.osgi.service.component.annotations.Component;

import java.util.Arrays;
import java.util.Map;

//MongoDB Importss
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

//Exceptions
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.bson.Document;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Other Imports
import com.aem.website.core.services.SaveDataService;

@Component(service = SaveDataService.class, immediate = true, property = {
		Constants.SERVICE_DESCRIPTION + "=Service to save data into MongoDB" })

public class SaveDataServiceimpl implements SaveDataService {

	public JSONObject saveDataTOMongoDB(Map<String, String[]> user) {

		JSONObject jsonResponse = new JSONObject();

		// Get values from the MAP
		String firstname = Arrays.asList(user.get("firstname")).get(0);
		String lastname = Arrays.asList(user.get("lastname")).get(0);
		String email = Arrays.asList(user.get("email")).get(0);
		String enquiry = Arrays.asList(user.get("enquiry")).get(0);

		try {

			// connect to MongoDB instance
			MongoClient mongoClient = new MongoClient("localhost", 27017);

			// Get DB and collection inside DB, DB and collection will be if not already
			// exist
			MongoDatabase db = mongoClient.getDatabase("userDetails");
			MongoCollection<Document> collection = db.getCollection("formData");

			// create document and insert all the values
			Document doc = new Document();
			doc.append("firstname", firstname);
			doc.append("lastname", lastname);
			doc.append("email", email);
			doc.append("enquiry", enquiry);
			collection.insertOne(doc);
			jsonResponse.put("status", "Success");

		} catch (JSONException e) {
			e.printStackTrace();
		}
		// returning JSON with a success message
		return jsonResponse;
	}
}

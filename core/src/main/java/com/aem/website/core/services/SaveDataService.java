package com.aem.website.core.services;

import org.apache.sling.commons.json.JSONObject;

import java.util.Map;

public interface SaveDataService {
    public JSONObject saveDataTOMongoDB(Map<String, String[]> userDetails);
}

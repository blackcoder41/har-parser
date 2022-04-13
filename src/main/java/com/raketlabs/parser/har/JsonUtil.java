package com.raketlabs.parser.har;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;

public class JsonUtil {
	
	public static JsonElement loadFile (String filename) throws FileNotFoundException {
		Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(filename));
        JsonElement json = gson.fromJson(reader, JsonElement.class);
        return json;
	}
}

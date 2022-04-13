package com.raketlabs.parser.har;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

import com.google.gson.JsonElement;


public class App {
    
    public static void main(String[] args) throws IOException {
        
        JsonElement config = JsonUtil.loadFile(Config.CONFIG_FILE).getAsJsonObject();
        JsonElement har = JsonUtil.loadFile(Config.HAR_FILE).getAsJsonObject();
        
        JsonElement entries = har
        		.getAsJsonObject().get("log")
        		.getAsJsonObject().get("entries");
        
        
        for (JsonElement entry : entries.getAsJsonArray()) {
        	JsonElement request = entry.getAsJsonObject().get("request");
        	JsonElement response = entry.getAsJsonObject().get("response");
        	
        	String url = request.getAsJsonObject().get("url").getAsString();
        	url = url.replace("http://localhost:8080", "");
        	
        	String text = response
            		.getAsJsonObject().get("content")
            		.getAsJsonObject().get("text")
            		.getAsString();
        	

        	File file = new File(url);
        	
        	System.out.println("Saving " + file.getAbsolutePath());

        	FileUtils.write(file, text, StandardCharsets.UTF_8);
        }
  
        System.out.println("end");
    }    
}

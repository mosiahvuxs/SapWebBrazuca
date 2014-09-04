package br.com.sapweb.brazuca.util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class WebServiceUtil {
	
	private WebResource webResource;
	private Client client;
	
	public WebServiceUtil(String url, String path){
		com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();

		client = Client.create(config);

		webResource = client.resource(url).path(path);

	}
	
	public WebServiceUtil(String url, String path, String path2){
		
		com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();

		client = Client.create(config);

		webResource = client.resource(url).path(path).path(path2);

	}	
	
	public String post(Object entity){
		return webResource.accept("application/xml").type("application/xml").post(String.class, entity);		
	}
	
	
}

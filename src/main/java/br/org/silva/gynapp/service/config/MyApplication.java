package br.org.silva.gynapp.service.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class MyApplication extends ResourceConfig{
	
	public MyApplication(){
		register(new ApplicationBinder());
		register(RequestContextFilter.class);
		packages("br.org.silva.gynapp.service");
	}

}

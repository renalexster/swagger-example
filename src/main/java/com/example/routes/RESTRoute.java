package com.example.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;

import com.example.model.User;
import com.example.service.HelloService;

public class RESTRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		restConfiguration().contextPath("/hello/rest").component("servlet").dataFormatProperty("prettyPrint", "true").bindingMode(RestBindingMode.auto)
		.host("0.0.0.0").port(8181).apiContextPath("/api-docs")
		.apiProperty("init.base.path", "hello/rest")
		.apiProperty("init.api.path", "/api-docs")
		.apiProperty("init.api.description", "Example Camel Swagger")
        .apiProperty("api.title", "Hello")
        .apiProperty("api.version", "1.0.0")
        .apiProperty("cors", "true");
		
		rest("/ping").get().produces("text/plain").route().routeId("teste").transform().constant("Hello.")
		.routeId("ping").endRest();
		
		rest("/user")
		.get()
			.param().name("page").type(RestParamType.query).description("Number page (not implemented)").defaultValue("1").required(Boolean.FALSE).endParam()
		.produces("application/json").route().bean(HelloService.class, "getListUser").endRest()
		.get("/{id}").route().bean(HelloService.class, "getUser").endRest()
		.delete("/{id}").produces("application/json").route().bean(HelloService.class, "removeUser").endRest()
		.post().type(User.class).produces("text/plain").route().bean(HelloService.class, "insertUser").setBody().constant("OK").endRest()
	;
		
	}

}

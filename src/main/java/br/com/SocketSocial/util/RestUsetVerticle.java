package br.com.SocketSocial.util;

import io.vertx.rxjava.core.AbstractVerticle;


public class RestUsetVerticle extends AbstractVerticle{

	public void start() throws Exception {
        System.out.println("BasicVerticle started");
        
        
        vertx.createHttpClient().getNow(8082, "localhost", "/api/private/user", resp -> {
            System.out.println("Got response " + resp.statusCode());
            resp.bodyHandler(body -> {
              System.out.println("Got data " + body.toString("ISO-8859-1"));
              
              vertx.eventBus().publish("feeds", body.toString("ISO-8859-1"));
            });
          });
        /*
        Router router = Router.router(vertx);
  	  
  	  HttpClient client = vertx.createHttpClient();
  	  
  	  HttpClientRequest req = client.request(HttpMethod.GET, 8080, "localhost", "/");*/
    }
}

package br.com.SocketSocial.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeEventType;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

public class FeedsVerticle extends AbstractVerticle{
	 

	public void start() throws Exception {

	    Router router = Router.router(vertx);
	    	    
	    BridgeOptions options = new BridgeOptions()
	    	.addOutboundPermitted(new PermittedOptions().setAddress("news-feed"))
	    	.addOutboundPermitted(new PermittedOptions().setAddress("news-feedComment"))
	    	.addOutboundPermitted(new PermittedOptions().setAddress("news-feedLike"));
	    
	    router.route("/syncfeeds/*").handler(SockJSHandler.create(vertx).bridge(options, event -> {

	      if (event.type() == BridgeEventType.SOCKET_CREATED) {
	        System.out.println("A socket was created");
	      }

	      // This signals that it's ok to process the event
	      event.complete(true);

	    }));

	    // Serve the static resources
	    router.route().handler(StaticHandler.create());
			    
	    vertx.setPeriodic(10000, (l) -> {
	    	  vertx.createHttpClient().getNow(8082, "localhost", "/api/private/feed/findfeeds/", resp -> {
	    	    resp.bodyHandler(body -> {
	    	    	vertx.eventBus().publish("news-feed", body.toString("ISO-8859-1"));
	    	    });
	    	  });
	    	});
	    
	    vertx.setPeriodic(10000, (l) -> {
	    	  vertx.createHttpClient().getNow(8082, "localhost", "/api/private/feedcomment/findfeedcomments/", resp -> {
	    	    resp.bodyHandler(body -> {
	    	    	vertx.eventBus().publish("news-feedComment", body.toString("ISO-8859-1"));
	    	    });
	    	  });
	    	});
	    
	    vertx.setPeriodic(10000, (l) -> {
	    	  vertx.createHttpClient().getNow(8082, "localhost", "/api/private/feedlike/findfeedlikes/", resp -> {
	    	    resp.bodyHandler(body -> {
	    	    	vertx.eventBus().publish("news-feedLike", body.toString("ISO-8859-1"));
	    	    });
	    	  });
	    	});
	    
	    vertx.createHttpServer().requestHandler(router::accept).listen(8085);

	   
	  }
}

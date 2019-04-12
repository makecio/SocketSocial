package br.com.SocketSocial.util;



import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;

import br.com.SocketSocial.resources.FeedsResource;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;


public class ChatVerticle extends AbstractVerticle{

	
	public void start() throws Exception {

	    Router router = Router.router(vertx);
	    
	    
	   

	    // Allow events for the designated addresses in/out of the event bus bridge
	    BridgeOptions opts = new BridgeOptions()
	      .addInboundPermitted(new PermittedOptions().setAddress("chat.to.server"))
	      .addOutboundPermitted(new PermittedOptions().setAddress("chat.to.client"));

	    // Create the event bus bridge and add it to the router.
	    SockJSHandler ebHandler = SockJSHandler.create(vertx).bridge(opts);
	    router.route("/eventbus/*").handler(ebHandler);

	    // Create a router endpoint for the static content.
	    router.route().handler(StaticHandler.create());

	    // Start the web server and tell it to use the router to handle requests.
	    vertx.createHttpServer().requestHandler(router::accept).listen(8084);

	    EventBus eb = vertx.eventBus();

	    // Register to listen for messages coming IN to the server
	    eb.consumer("chat.to.server").handler(message -> {
	      // Create a timestamp string
	      String timestamp = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(Date.from(Instant.now()));
	      // Send the message back out to all clients with the timestamp prepended.
	      eb.publish("chat.to.client", timestamp + ": " + message.body());
	      
	      //vertx.deployVerticle(new RestUsetVerticle());
	      
	      vertx.deployVerticle(new FeedsResource());
	      
	      
	    });
	    
	    //vertx.deployVerticle(new RestUsetVerticle());
	    vertx.deployVerticle(new FeedsResource());
	    
	    // Register to listen for messages coming IN to the server
	    eb.consumer("feed").handler(message -> {
	      // Create a timestamp string
	      String timestamp = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(Date.from(Instant.now()));
	      // Send the message back out to all clients with the timestamp prepended.
	      eb.publish("chat.to.client", timestamp + ": " + message.body());
	      
	      
	      
	      
	    });
	

	  }
}

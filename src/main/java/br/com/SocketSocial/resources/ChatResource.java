package br.com.SocketSocial.resources;

import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeEventType;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;


public class ChatResource extends AbstractVerticle{

	
	public void start() throws Exception {

	    Router router = Router.router(vertx);

	    

	    // Allow outbound traffic to the news-feed address

	    BridgeOptions options = new BridgeOptions().addOutboundPermitted(new PermittedOptions().setAddress("news-feed"));

	    router.route("/eventbus/*").handler(SockJSHandler.create(vertx).bridge(options, event -> {

	      // You can also optionally provide a handler like this which will be passed any events that occur on the bridge
	      // You can use this for monitoring or logging, or to change the raw messages in-flight.
	      // It can also be used for fine grained access control.

	      if (event.type() == BridgeEventType.SOCKET_CREATED) {
	        System.out.println("A socket was created");
	      }

	      // This signals that it's ok to process the event
	      event.complete(true);

	    }));

	    // Serve the static resources
	    router.route().handler(StaticHandler.create());
	    String teste="[{name:'ADRIANO','surname':'RAGGIO','active':1,'created':1474599600000,'modified':1474599600000,'deleted':null,'token':'123','token_expire_date':1474599600000,'cliente':1,'email':'adriano.raggio@gmail.com','password':'123'}]";
 	   

	    vertx.createHttpServer().requestHandler(router::accept).listen(8086);

	    // Publish a message to the address "news-feed" every second
	    vertx.setPeriodic(1000, t -> vertx.eventBus().publish("news-feed", teste));
	  }
}

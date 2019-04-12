package br.com.SocketSocial.resources;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import br.com.SocketSocial.model.Feed;
import br.com.SocketSocial.model.FeedComment;
import br.com.SocketSocial.model.FeedLike;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import io.vertx.core.MultiMap;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.handler.sockjs.BridgeEventType;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.buffer.Buffer;
import io.vertx.rxjava.core.eventbus.EventBus;
import io.vertx.rxjava.core.http.HttpClient;
import io.vertx.rxjava.core.http.HttpClientRequest;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.handler.sockjs.SockJSHandler;

public class FeedsResource extends AbstractVerticle{

	private static final int CONNS = 3;
	
	public void start() throws Exception {
		
        System.out.println("FeedsResource started");
        
        EventBus eb = vertx.eventBus();
           	  
        Router router = Router.router(vertx);
        
	    BridgeOptions options = new BridgeOptions();
	    
	    PermittedOptions inBoundPermittedGET = new PermittedOptions().setAddressRegex("get\\..+");
	    PermittedOptions inBoundPermittedPOST = new PermittedOptions().setAddressRegex("post\\..+");
	    PermittedOptions outBoundPermittedNEWS = new PermittedOptions().setAddressRegex("news\\..+");
	    
	    options.addInboundPermitted(inBoundPermittedGET)
	    		.addInboundPermitted(inBoundPermittedPOST)
	    			.addOutboundPermitted(outBoundPermittedNEWS);
	    
        
        router.route("/feeds/*").handler(SockJSHandler.create(vertx).bridge(options, event -> {

        if (event.type() == BridgeEventType.SOCKET_CREATED) {
        		System.out.println("A socket was created");
        	}

  	    event.complete(true);
  	      
  	    HttpClientOptions httpOptions = new HttpClientOptions().setKeepAlive(true).setMaxPoolSize(CONNS).setDefaultPort(8082);
        HttpClient client = vertx.createHttpClient(httpOptions);
        
        
  	    /*** POST FEED***/
  	    eb.consumer("post.feed").handler(message -> {
  	        	
  	    	
  	    	HttpClientRequest request = client.post("http://localhost:8082/api/private/feed/saveFeed/", response -> {
  	    	     	    	
  	    		response.bodyHandler(body -> {
  	    			
  	    			Feed feed = new Gson().fromJson(message.body().toString(), new TypeToken<Feed>(){}.getType());	
  	    			
  	    				vertx.eventBus().send("news.savefeed"+feed.getIdUser().toString(), body.toString("ISO-8859-1"));
  		    	    });
  		    	    
  	    	  });
  	    	
  	      request.putHeader("content-length", "10000");
  	      request.putHeader("content-type", "application/json");
  	      request.write(message.body().toString());
  	      request.end();
  	      //client.close();
  	    });

  	  /*** POST FEEDCOOMENT***/
  	    eb.consumer("post.feedComment").handler(message -> {
  	        	
  	    	
  	    	HttpClientRequest request = client.post("http://localhost:8082/api/private/feedcomment/saveFeedComment/", response -> {
  	    	     	    	
  	    		response.bodyHandler(body -> {
  	    			
  	    			FeedComment feedComment = new Gson().fromJson(message.body().toString(), new TypeToken<FeedComment>(){}.getType());	
  	    			
  	    				vertx.eventBus().send("news.savefeedComment"+feedComment.getIdUser().toString(), body.toString("ISO-8859-1"));
  		    	    });
  		    	    
  	    	  });
  	   	
  	      request.putHeader("content-length", "10000");
  	      request.putHeader("content-type", "application/json");
  	      request.write(message.body().toString());
  	      request.end();
  	      //client.close();
  	    });
  	    
  	    /*** POST FEEDLIKE***/
	  	    eb.consumer("post.feedLike").handler(message -> {
	  	        	
	  	    	
	  	    	HttpClientRequest request = client.post("http://localhost:8082/api/private/feedlike/saveFeedLike/", response -> {
	  	    	     	    	
	  	    		response.bodyHandler(body -> {
	  	    			
	  	    			FeedLike feedLike = new Gson().fromJson(message.body().toString(), new TypeToken<FeedLike>(){}.getType());	
	  	    			System.out.println(message.body().toString());
	  	    				vertx.eventBus().send("news.savefeedLikes"+feedLike.getIdUser().toString(), body.toString("ISO-8859-1"));
	  		    	    });
	  		    	    
	  	    	  });
	  	    	
	  	  request.putHeader("content-length", "10000");
	      request.putHeader("content-type", "application/json");
	      request.write(message.body().toString());
	      request.end();
	      //client.close();
	    });
	  	    
  	    })); // FIM DA ROUTE
        
       
        // INICIA LISTEN DA ROTA
        vertx.createHttpServer().requestHandler(router::accept).listen(8086);
 
       
    }
	

}


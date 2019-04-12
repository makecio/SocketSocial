package br.com.SocketSocial;


import br.com.SocketSocial.resources.FeedsResource;
import br.com.SocketSocial.util.RestUsetVerticle;
import br.com.SocketSocial.verticle.FeedsVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.rxjava.core.AbstractVerticle;


public class VertxMain extends AbstractVerticle 
{

   public static void main(String[] args)
   {
 
      Runner.runExample(VertxMain.class);
      
      Vertx vertx = Vertx.vertx();
      
	  vertx.deployVerticle(new FeedsVerticle(), new Handler<AsyncResult<String>>() {
		    @Override
		    public void handle(AsyncResult<String> stringAsyncResult) {
		        System.out.println("deployVerticle FeedsVerticle completed");
		        }
		    });
	   
	   vertx.deployVerticle(new FeedsResource(), new Handler<AsyncResult<String>>() {
		    @Override
		    public void handle(AsyncResult<String> stringAsyncResult) {
		        System.out.println("deployVerticle FeedsResource completed");
		        }
		    });

   
}

	 
   
}

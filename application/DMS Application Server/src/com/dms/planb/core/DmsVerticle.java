package com.dms.planb.core;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;

public class DmsVerticle extends AbstractVerticle {
	private HttpServer server;
	
	@Override
	public void start() throws Exception {
		// Logging : Server started
		server = vertx.createHttpServer();
		server.requestHandler(request -> {
			// Logging : Request from client
			
			if(request.method() == HttpMethod.POST) {
				request.bodyHandler(buffer -> {
					
				});
			}
		});
	}
	
	public void stop(Future stopFuture) throws Exception {
		
	}
}
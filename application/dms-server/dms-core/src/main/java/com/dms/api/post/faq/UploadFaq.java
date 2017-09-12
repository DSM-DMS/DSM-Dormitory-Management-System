package com.dms.api.post.faq;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;

import com.dms.utilities.database.DataBase;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path="/post/faq", method={HttpMethod.POST})
public class UploadFaq implements Handler<RoutingContext> {
	public UploadFaq() {
		
	}
	
	@Override
	public void handle(RoutingContext ctx) {

		if (!Guardian.isAdmin(ctx)) {
			ctx.response().setStatusCode(400).end();
			ctx.response().close();
			return;
		}
		
		DataBase database = DataBase.getInstance();
		
		String title = ctx.request().getParam("title");
		String content = ctx.request().getParam("content");
		
		if(!Guardian.checkParameters(title, content)) {
            ctx.response().setStatusCode(400).end();
            ctx.response().close();
        	return;
        }
		
		try {
			database.executeUpdate("INSERT INTO faq(title, content) VALUES('", title, "', '", content, "')");
			
			ctx.response().setStatusCode(201).end();
			ctx.response().close();
		} catch(SQLException e) {
			ctx.response().setStatusCode(500).end();
			ctx.response().close();
			
			Log.l("SQLException");
		}
	}
}

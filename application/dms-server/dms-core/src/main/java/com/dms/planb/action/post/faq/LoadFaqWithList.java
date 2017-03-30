package com.dms.planb.action.post.faq;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.actions.support.PrecedingWork;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonArray;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/post/faq/list", method={HttpMethod.GET})
public class LoadFaqWithList implements Handler<RoutingContext> {
	public LoadFaqWithList() {
		
	}
	
	@Override
	public void handle(RoutingContext context) {
		context = PrecedingWork.putHeaders(context);
		
		DataBase database = DataBase.getInstance();
		SafeResultSet resultSet;
		EasyJsonObject responseObject = new EasyJsonObject();
		EasyJsonObject tempObject = new EasyJsonObject();
		EasyJsonArray tempArray = new EasyJsonArray();
		
		try {
			if(!context.request().params().contains("page") && !context.request().params().contains("limit")) {
				resultSet = database.executeQuery("SELECT * FROM faq");
			} else {
				int page = Integer.parseInt(context.request().getParam("page"));
				int limit = Integer.parseInt(context.request().getParam("limit"));
				resultSet = database.executeQuery("SELECT * FROM faq limit ", ((page - 1) * limit), ", ", limit);
			}
			
			int postCount = 0;
			if(resultSet.next()) {
				do {
					tempObject = new EasyJsonObject();
					
					tempObject.put("no", resultSet.getInt("no"));
					tempObject.put("title", resultSet.getString("title"));
					tempObject.put("content", resultSet.getString("content"));
					
					tempArray.add(tempObject);
					
					postCount++;
				} while(resultSet.next());
				
				responseObject.put("num_of_post", postCount);
				responseObject.put("result", tempArray);
				
				context.response().setStatusCode(200);
				context.response().end(responseObject.toString());
				context.response().close();
			} else {
				context.response().setStatusCode(204).end();
				context.response().close();
			}
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}

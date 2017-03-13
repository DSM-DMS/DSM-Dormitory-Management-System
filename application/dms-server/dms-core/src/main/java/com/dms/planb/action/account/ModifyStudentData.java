package com.dms.planb.action.account;

import java.sql.SQLException;

import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;

import com.dms.planb.support.PrecedingWork;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/account/student", method={HttpMethod.PATCH})
public class ModifyStudentData implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		context = PrecedingWork.putHeaders(context);
		
		DataBase database = DataBase.getInstance();
		
		String id = context.request().getParam("id");
		String uid = null;
		try {
			uid = UserManager.getUid(id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		int number = Integer.parseInt(context.request().getParam("number"));
		String name = context.request().getParam("name");
		
		try {
			database.executeUpdate("UPDATE student_data SET number=", number, " WHERE uid='", uid, "'");
			database.executeUpdate("UPDATE student_data SET name='", name, "' WHERE uid='", uid, "'");
			
			context.response().setStatusCode(200).end();
			context.response().close();
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}

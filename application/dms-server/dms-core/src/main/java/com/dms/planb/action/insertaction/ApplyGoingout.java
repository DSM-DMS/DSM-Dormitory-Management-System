package com.dms.planb.action.insertaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.json.EasyJsonObject;

@ActionRegistration(command=133)
public class ApplyGoingout implements Actionable {
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		/**
		 * Apply goingout - about departure date and reason
		 * 
		 * Table Name : goingout_apply
		 * 
		 * id VARCHAR(20) PK NN
		 * dept_date DATE NN
		 * reason VARCHAR(100) NN
		 * 
		 * DATE format : YYYY-MM-DD
		 */
		String id = requestObject.getString("id");
		String deptDate = requestObject.getString("dept_date");
		String reason = requestObject.getString("reason");
		
		int status = database.executeUpdate("INSERT INTO goingout_apply(id, dept_date, reason) VALUES('", id, "', '", deptDate, "', '", reason, "')");
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
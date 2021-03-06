package com.dms.api.account.login;

import java.sql.SQLException;

import com.dms.account_manager.Guardian;
import com.dms.account_manager.UserManager;
import com.dms.secure.SecureManager;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/account/login/student", method = {HttpMethod.POST})
public class LoginStudentRequest implements Handler<RoutingContext> {
    private SecureManager secureManager;
    private SecureManager loginRequestSecureManager;

    public LoginStudentRequest() {
        secureManager = SecureManager.create(this.getClass());
        loginRequestSecureManager = SecureManager.create("StudentLoginRequest", 5,30);
    }

    @Override
    public void handle(RoutingContext ctx) {
        String id = ctx.request().getParam("id");
        String password = ctx.request().getParam("password");
        String remember = ctx.request().getParam("remember");
        remember = (remember == null) ? "false" : "true";

        if (!Guardian.checkParameters(id, password, remember)) {
            ctx.response().setStatusCode(400).end();
            ctx.response().close();
            secureManager.invalidRequest(ctx);
            return;
        }

            try {
                boolean check = UserManager.login(id, password);
                if (check) {
                    UserManager.registerSession(ctx, Boolean.valueOf(remember), id);

                    ctx.response().setStatusCode(201).end("<script>window.location.href=document.referrer;</script>");
                    ctx.response().close();
                } else {
                    loginRequestSecureManager.invalidRequest(ctx);
                    ctx.response().setStatusCode(204).end();
                    ctx.response().close();
                }
            } catch (SQLException e) {
                ctx.response().setStatusCode(500).end();
                ctx.response().close();
                secureManager.invalidRequest(ctx);
                Log.l("SQLException");
            }

        Log.l("Login Request (", id, ", ", ctx.request().remoteAddress(), ") status : " + ctx.response().getStatusCode());
    }
}

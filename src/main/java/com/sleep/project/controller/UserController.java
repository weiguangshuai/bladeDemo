package com.sleep.project.controller;

import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.blade.mvc.http.Session;
import com.sleep.project.common.Constants;
import com.sleep.project.pojo.User;


/**
 * @author weigs
 * @date 17-12-17
 */
@Path
public class UserController {
    @GetRoute("index")
    public String index() {
        return "index.html";
    }

    @GetRoute("login")
    public String login() {
        return "login.html";
    }

    @PostRoute("login")
    public String doLogin(User user, Request request, Response response) {
        if (!Constants.USERNAME.equalsIgnoreCase(user.getUsername()) ||
                !Constants.PASSWORD.equalsIgnoreCase(user.getPassword())) {

            request.attribute("error", "用户名或密码错误");
            return "login.html";
        }

        request.session().attribute(Constants.LOGIN_SESSION_KEY, user.getUsername());

        response.redirect("/index");

        return null;
    }

    @GetRoute("logout")
    public void logout(Session session, Response response) {
        session.removeAttribute(Constants.LOGIN_SESSION_KEY);
        response.redirect("/login");
    }
}

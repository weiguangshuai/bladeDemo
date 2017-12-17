package com.sleep.project;

import com.blade.Blade;
import com.blade.Environment;
import com.blade.event.EventType;
import com.blade.kit.StringKit;
import com.sleep.project.common.Constants;

/**
 * @author weigs
 * @date 17-12-17
 */
public class Application {
    public static void main(String[] args) {
        Blade.me()
                .before("/*", (((request, response) -> {
                    String uri = request.uri();
                    if ("/index".equals(uri)) {
                        String username = request.session().attribute(Constants.LOGIN_SESSION_KEY);
                        if (StringKit.isBlank(username)) {
                            response.redirect("/login");
                            return;
                        }
                    }
                }))).event(EventType.SERVER_STARTED, (event -> {
            Environment environment = event.blade.environment();
            Constants.USERNAME = environment.get("app.username").get();
            Constants.PASSWORD = environment.get("app.password").get();
        })).start(Application.class);
    }
}

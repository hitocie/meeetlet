package com.meeetlet.controller.api.v1.me;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class UpdateController extends Controller {

    @Override
    public Navigation run() throws Exception {
        return forward("../error/no_service.html");
    }
}

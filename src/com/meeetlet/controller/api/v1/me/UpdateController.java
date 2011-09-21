package com.meeetlet.controller.api.v1.me;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.meeetlet.common.Const;

public class UpdateController extends Controller {

    @Override
    public Navigation run() throws Exception {

        response.setContentType(Const.charEncoding);

        return forward("../error/no_service.html");
    }
}

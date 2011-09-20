package com.meeetlet.controller.api.v1.event;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;

import com.meeetlet.controller.api.v1.event.UpdateController;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class UpdateControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/api/v1/event/update");
        UpdateController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is(nullValue()));
    }
}

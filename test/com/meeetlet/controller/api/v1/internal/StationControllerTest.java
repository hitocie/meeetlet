package com.meeetlet.controller.api.v1.internal;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class StationControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/api/v1/internal/station");
        StationController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is(nullValue()));
    }
}

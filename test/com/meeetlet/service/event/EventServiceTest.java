package com.meeetlet.service.event;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import com.meeetlet.service.event.EventService;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class EventServiceTest extends AppEngineTestCase {

    private EventService service = new EventService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}

package com.meeetlet.model.event;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import com.meeetlet.model.event.Event;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class EventTest extends AppEngineTestCase {

    private Event model = new Event();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}

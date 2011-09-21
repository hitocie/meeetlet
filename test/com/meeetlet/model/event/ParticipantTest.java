package com.meeetlet.model.event;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ParticipantTest extends AppEngineTestCase {

    private Participant model = new Participant();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}

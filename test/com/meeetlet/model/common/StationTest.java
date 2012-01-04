package com.meeetlet.model.common;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class StationTest extends AppEngineTestCase {

    private Station model = new Station();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}

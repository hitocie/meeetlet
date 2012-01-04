package com.meeetlet.service.common;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AreaServiceTest extends AppEngineTestCase {

    private AreaService service = new AreaService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}

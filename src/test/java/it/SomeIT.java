package it;

import org.elasticsearch.client.Client;
import org.elasticsearch.test.ESIntegTestCase;
import org.junit.Test;

public class SomeIT extends ESIntegTestCase {

    @Test
    public void testName() throws Exception {
        Client client = client();
    }
}

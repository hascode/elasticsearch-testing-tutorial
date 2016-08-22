package it;

import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.elasticsearch.client.Client;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.elasticsearch.test.ESIntegTestCase;
import org.elasticsearch.test.ESIntegTestCase.ClusterScope;
import org.elasticsearch.test.ESIntegTestCase.Scope;
import org.junit.Test;

import com.carrotsearch.randomizedtesting.annotations.ThreadLeakScope;
import com.hascode.tutorial.control.BeerSearch;
import com.hascode.tutorial.entity.Beer;

@ClusterScope(scope = Scope.SUITE)
@ThreadLeakScope(ThreadLeakScope.Scope.NONE)
public class SomeIntegrationTest extends ESIntegTestCase {
    @Test
    public void shouldIndexAndSearchBeers() throws Exception {
        Client client = client();

        BeerSearch search = new BeerSearch(client);
        search.add(new Beer("1", "Becks", "mild", "tasty"));
        search.add(new Beer("2", "Holsten", "crisp", "strong"));

        assertThat(ensureGreen("drinks"), equalTo(ClusterHealthStatus.GREEN));

        List<Beer> strongBeers = search.findByTag("strong");
        assertThat(strongBeers.size(), equalTo(1));

        Beer strongBeer = strongBeers.get(0);
        assertThat(strongBeer.getName(), equalTo("Holsten"));
    }
}

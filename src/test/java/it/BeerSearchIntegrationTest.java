package it;

import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.elasticsearch.client.Client;
import org.elasticsearch.test.ESIntegTestCase;
import org.elasticsearch.test.ESIntegTestCase.ClusterScope;
import org.elasticsearch.test.ESIntegTestCase.Scope;
import org.junit.Test;

import com.carrotsearch.randomizedtesting.annotations.ThreadLeakScope;
import com.hascode.tutorial.control.BeerSearch;
import com.hascode.tutorial.entity.Beer;

@ClusterScope(scope = Scope.SUITE)
@ThreadLeakScope(ThreadLeakScope.Scope.NONE)
public class BeerSearchIntegrationTest extends ESIntegTestCase {
    @Test
    public void shouldIndexAndSearchBeers() throws Exception {
        Client client = client();

        BeerSearch search = new BeerSearch(client);
        search.add(new Beer("1", "Becks", "mild", "tasty"));
        search.add(new Beer("2", "Holsten", "crisp", "strong"));

        refresh(); // otherwise we would not find beers yet

        indexExists("drinks"); // verifies that index 'drinks' exists
        ensureGreen("drinks"); // ensures cluster status is green

        List<Beer> strongBeers = search.findByTag("strong");
        assertThat(strongBeers.size(), equalTo(1));

        Beer strongBeer = strongBeers.get(0);
        assertThat(strongBeer.getName(), equalTo("Holsten"));
    }
}

package com.hascode.tutorial.control;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;

import com.hascode.tutorial.entity.Beer;

public class BeerSearch {
    private static final String TYPE_BEER = "beer";
    private static final String INDEX = "drinks";

    private final Client client;

    public BeerSearch(Client client) {
        this.client = client;
    }

    /**
     * Adds a given beer to the search index.
     * @param beer the beer.
     */
    public void add(Beer beer) {
        System.out.printf("adding beer to search index: %s\n", beer);
        IndexRequest indexRequest = new IndexRequest(INDEX, TYPE_BEER, beer.getId());
        indexRequest.source(beer.toJson());
        IndexResponse response = client.index(indexRequest).actionGet();
        System.out.printf("entry added to index '%s', type '%s', doc-version: '%s', doc-id: '%s', created: %s\n",
                response.getIndex(), response.getType(), response.getVersion(), response.getId(), response.isCreated());
    }

    /**
     * Searches beers by given tag
     * @param tag the tag
     * @return a list of beers
     */
    public List<Beer> findByTag(String tag) {
        System.out.printf("searching beers for given tag: %s\n", tag);
        SearchResponse response = client.prepareSearch(INDEX).setTypes(TYPE_BEER).addFields("name", "tags")
                .setQuery(QueryBuilders.termQuery("tags", tag)).execute().actionGet();
        SearchHits hits = response.getHits();
        System.out.printf("%s hits for tag '%s' found\n", hits.totalHits(), tag);

        return StreamSupport.stream(hits.spliterator(), true).map(hit -> {
            String name = hit.field("name").getValue();
            String[] tags = hit.field("tags").getValues().toArray(new String[] {});
            return new Beer(hit.getId(), name, tags);
        }).collect(Collectors.toList());
    }

}

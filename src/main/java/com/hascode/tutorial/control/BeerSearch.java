package com.hascode.tutorial.control;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;

import com.hascode.tutorial.entity.Beer;

public class BeerSearch {
    private final Client client;

    public BeerSearch(Client client) {
        this.client = client;
    }

    public void add(Beer beer) {
        System.out.printf("adding beer to search index: %s\n", beer);
        IndexRequest indexRequest = new IndexRequest("drinks", "beer", beer.getId());
        indexRequest.source(beer.toJson());
        IndexResponse response = client.index(indexRequest).actionGet();
        System.out.printf("index entry for type %s successfully created, doc-version: %s\n", response.getType(),
                response.getVersion());
    }

    public List<Beer> findByTag(String tag) {
        System.out.printf("searching beers for given tag: %s\n", tag);
        return new ArrayList<>();

    }

}

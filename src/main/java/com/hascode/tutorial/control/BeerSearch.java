package com.hascode.tutorial.control;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.client.Client;

import com.hascode.tutorial.entity.Beer;

public class BeerSearch {
    private final Client client;

    public BeerSearch(Client client) {
        this.client = client;
    }

    public void add(Beer beer) {
        System.out.printf("adding beer to search index: %s\n", beer);
    }

    public List<Beer> findByTag(String tag) {
        System.out.printf("adding beer for given tag: %s\n", tag);
        return new ArrayList<>();

    }

}

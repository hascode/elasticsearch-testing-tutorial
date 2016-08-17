package com.hascode.tutorial.boundary;

import java.util.List;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

import com.hascode.tutorial.control.BeerSearch;
import com.hascode.tutorial.entity.Beer;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Settings settings = Settings.builder().put("path.home", "/tmp").build();
        Node server = NodeBuilder.nodeBuilder().settings(settings).build();
        final String clusterName = server.settings().get("cluster.name");

        System.out.printf("starting server with cluster-name: %s\n", clusterName);
        server.start();
        Thread.sleep(2000);
        Client client = server.client();

        BeerSearch search = new BeerSearch(client);
        search.add(new Beer("Becks", 7, "mild", "tasty"));
        List<Beer> beers = search.findByTag("tasty");
        beers.forEach(System.out::println);

        System.out.printf("closing server with cluster-name: %s\n", clusterName);
        server.close();
    }

}

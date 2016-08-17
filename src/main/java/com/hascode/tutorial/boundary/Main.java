package com.hascode.tutorial.boundary;

import java.io.File;
import java.util.List;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

import com.google.common.io.Files;
import com.hascode.tutorial.control.BeerSearch;
import com.hascode.tutorial.entity.Beer;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        File tempDir = Files.createTempDir();
        Settings settings = Settings.builder().put("path.home", tempDir.getAbsolutePath()).build();
        Node server = NodeBuilder.nodeBuilder().settings(settings).build();
        final String clusterName = server.settings().get("cluster.name");

        System.out.printf("starting server with cluster-name: %s\n", clusterName);
        server.start();
        Thread.sleep(2000);
        Client client = server.client();

        BeerSearch search = new BeerSearch(client);
        search.add(new Beer("1", "Becks", "mild", "tasty"));
        search.add(new Beer("2", "Holsten", "crisp", "strong"));
        search.add(new Beer("3", "Kilkenny", "mild", "sweet"));
        search.add(new Beer("4", "Budvar", "tasty", "crispy"));
        Thread.sleep(2000);
        List<Beer> beers = search.findByTag("tasty");
        beers.forEach(System.out::println);

        System.out.printf("closing server with cluster-name: %s\n", clusterName);
        server.close();
    }

}

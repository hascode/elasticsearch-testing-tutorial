package com.hascode.tutorial.entity;

import java.util.Arrays;
import java.util.UUID;

public class Beer {
    private final String id;
    private final String name;
    private final int ratingOneOfTen;
    private final String[] tags;

    public Beer(String name, int ratingOneOfTen, String... tags) {
        this.id = UUID.randomUUID().toString().toUpperCase();
        this.name = name;
        this.ratingOneOfTen = ratingOneOfTen;
        this.tags = tags.clone();
    }

    public String toJson() {
        return String.format("{\"name\":\"%s\",\"ratingOfTen\":%s,\"tags\":[]}", name, ratingOneOfTen);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRatingOneOfTen() {
        return ratingOneOfTen;
    }

    public String[] getTags() {
        return tags.clone();
    }

    @Override
    public String toString() {
        return "Beer [id=" + id + ", name=" + name + ", ratingOneOfTen=" + ratingOneOfTen + ", tags="
                + Arrays.toString(tags) + "]";
    }

}

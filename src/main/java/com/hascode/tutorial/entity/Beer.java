package com.hascode.tutorial.entity;

import java.util.Arrays;

public class Beer {
    private final String name;
    private final int ratingOneOfTen;
    private final String[] tags;

    public Beer(String name, int ratingOneOfTen, String... tags) {
        this.name = name;
        this.ratingOneOfTen = ratingOneOfTen;
        this.tags = tags.clone();
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
        return "Beer [name=" + name + ", ratingOneOfTen=" + ratingOneOfTen + ", tags=" + Arrays.toString(tags) + "]";
    }

}

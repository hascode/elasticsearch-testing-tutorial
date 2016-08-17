package com.hascode.tutorial.entity;

import java.util.Arrays;

public class Beer {
    private final String id;
    private final String name;
    private final String[] tags;

    public Beer(String id, String name, String... tags) {
        this.id = id;
        this.name = name;
        this.tags = tags.clone();
    }

    public String toJson() {
        return String.format("{\"name\":\"%s\",\"tags\":[%s]}", name,
                Arrays.stream(tags).map(tag -> "\"" + tag + "\"").reduce((p, c) -> p + ", " + c).get());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String[] getTags() {
        return tags.clone();
    }

    @Override
    public String toString() {
        return "Beer [id=" + id + ", name=" + name + ", tags=" + Arrays.toString(tags) + "]";
    }

}

package com.keepit.Id;

public class Id {
    private String id;

    public Id(){

    }

    public Id(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Id = "+id+"";
    }
}

package com.begmyratmammedov.rocketick.model;

public class Patch {
    String small;
    String large;

    public Patch(){}

    public Patch(String small, String large) {
        this.small = small;
        this.large = large;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}

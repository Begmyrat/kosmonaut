package com.begmyratmammedov.rocketick.model;

public class Links {
    Patch patch;

    public Links(){}

    public Links(Patch patch) {
        this.patch = patch;
    }

    public Patch getPatch() {
        return patch;
    }

    public void setPatch(Patch patch) {
        this.patch = patch;
    }
}

package com.begmyratmammedov.rocketick.model;

public class LaunchModel {

    Links links;
    String static_fire_date_utc;
    String static_fire_date_unix;
    String rocket;
    Boolean success;
    String details;
    Integer flight_number;
    String name;
    String id;

    public LaunchModel(){}

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public String getStatic_fire_date_utc() {
        return static_fire_date_utc;
    }

    public void setStatic_fire_date_utc(String static_fire_date_utc) {
        this.static_fire_date_utc = static_fire_date_utc;
    }

    public String getStatic_fire_date_unix() {
        return static_fire_date_unix;
    }

    public void setStatic_fire_date_unix(String static_fire_date_unix) {
        this.static_fire_date_unix = static_fire_date_unix;
    }

    public String getRocket() {
        return rocket;
    }

    public void setRocket(String rocket) {
        this.rocket = rocket;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(Integer flight_number) {
        this.flight_number = flight_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

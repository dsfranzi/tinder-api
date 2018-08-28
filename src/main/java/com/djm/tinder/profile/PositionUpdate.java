package com.djm.tinder.profile;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PositionUpdate {
    /**
     * Position's Latitude
     */
    @JsonProperty("lat")
    private double lat;

    /**
     * Position's longitude
     */
    @JsonProperty("lon")
    private double lon;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public static PositionUpdate fromPosition(Position position) {
        PositionUpdate positionUpdate = new PositionUpdate();
        positionUpdate.setLat(position.getLat());
        positionUpdate.setLon(position.getLon());
        return positionUpdate;
    }
}

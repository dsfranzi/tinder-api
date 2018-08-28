package com.djm.tinder.profile;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author Diego Mariani
 * @since 05-2017
 */
public class Position {

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

    /**
     * Last time this position has been registered
     */
    @JsonProperty("at")
    private Date lastRecordedAt;

    /**
     * @return latitude
     */
    public double getLat() {
        return lat;
    }

    /**
     * @param lat Position's latitude
     * @return Position's object
     */
    public Position setLat(double lat) {
        this.lat = lat;
        return this;
    }

    /**
     * @return longitude
     */
    public double getLon() {
        return lon;
    }

    /**
     * @param lon Position's longitude
     * @return Position's object
     */
    public Position setLon(double lon) {
        this.lon = lon;
        return this;
    }

    /**
     * @return last time position has been registered
     */
    public Date getLastRecordedAt() {
        return lastRecordedAt;
    }

    /**
     * @param lastRecordedAt
     * @return Position's object
     */
    public Position setLastRecordedAt(Date lastRecordedAt) {
        this.lastRecordedAt = lastRecordedAt;
        return this;
    }

    public static Position Builder() {
        return new Position();
    }
}

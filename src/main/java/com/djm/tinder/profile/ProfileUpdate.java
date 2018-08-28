package com.djm.tinder.profile;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfileUpdate {
    @JsonProperty(value = "age_filter_max")
    private int ageFilterMax;

    @JsonProperty(value = "age_filter_min")
    private int ageFilterMin;

    @JsonProperty(value = "gender")
    private int gender;

    @JsonProperty(value = "gender_filter")
    private int genderFilter;

    @JsonProperty(value = "distance_filter")
    private int distanceFilter;

    public int getAgeFilterMax() {
        return ageFilterMax;
    }

    public void setAgeFilterMax(int ageFilterMax) {
        this.ageFilterMax = ageFilterMax;
    }

    public int getAgeFilterMin() {
        return ageFilterMin;
    }

    public void setAgeFilterMin(int ageFilterMin) {
        this.ageFilterMin = ageFilterMin;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getGenderFilter() {
        return genderFilter;
    }

    public void setGenderFilter(int genderFilter) {
        this.genderFilter = genderFilter;
    }

    public int getDistanceFilter() {
        return distanceFilter;
    }

    public void setDistanceFilter(int distanceFilter) {
        this.distanceFilter = distanceFilter;
    }

    public static ProfileUpdate fromProfile(Profile profile) {
        ProfileUpdate profileUpdate = new ProfileUpdate();
        profileUpdate.setAgeFilterMin(profile.getAgeFilterMin());
        profileUpdate.setAgeFilterMax(profile.getAgeFilterMax());
        profileUpdate.setGender(profile.getGender());
        profileUpdate.setGenderFilter(profile.getGenderFilter());
        profileUpdate.setDistanceFilter(profile.getDistanceFilter());
        return profileUpdate;
    }
}

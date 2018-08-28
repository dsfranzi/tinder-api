package com.djm.tinder.profile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Diego Mariani
 * @since 05-2017
 */
public class Profile {

    @JsonProperty(value = "_id", access = JsonProperty.Access.READ_ONLY)
    private String id;

    @JsonProperty(value = "age_filter_max")
    private int ageFilterMax;

    @JsonProperty(value = "age_filter_min")
    private int ageFilterMin;

    @JsonProperty(value = "bio", access = JsonProperty.Access.READ_ONLY)
    private String bio;

    @JsonProperty(value = "birth_date", access = JsonProperty.Access.READ_ONLY)
    private Date birthDate;

    @JsonProperty(value = "blend", access = JsonProperty.Access.READ_ONLY)
    private String blend;

    @JsonProperty(value = "can_create_squad", access = JsonProperty.Access.READ_ONLY)
    private boolean canCreateSquad;

    @JsonIgnore
    private int ConnectionCount;

    @JsonProperty(value = "create_date", access = JsonProperty.Access.READ_ONLY)
    private Date createDate;

    @JsonProperty(value = "discoverable_party", access = JsonProperty.Access.READ_ONLY)
    private boolean discoverable;

    @JsonProperty(value = "distance_filter")
    private int distanceFilter;

    @JsonProperty(value = "facebook_id", access = JsonProperty.Access.READ_ONLY)
    private String facebookId;

    @JsonProperty(value = "gender")
    private int gender;

    @JsonProperty(value = "gender_filter")
    private int genderFilter;

    @JsonProperty(value = "name", access = JsonProperty.Access.READ_ONLY)
    private String name;

    @JsonProperty(value = "username", access = JsonProperty.Access.READ_ONLY)
    private String username;

    @JsonProperty(value = "pos", access = JsonProperty.Access.READ_ONLY)
    private Position position;

    @JsonProperty(value = "photo_optimizer_enabled", access = JsonProperty.Access.READ_ONLY)
    private boolean photoOptimizerEnabled;

    @JsonProperty(value = "photos", access = JsonProperty.Access.READ_ONLY)
    private ArrayList<Photo> photos;

    public String getId() {
        return id;
    }

    public Profile setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * @return max age filter
     */
    public int getAgeFilterMax() {
        return ageFilterMax;
    }

    public Profile setAgeFilterMax(int ageFilterMax) {
        this.ageFilterMax = ageFilterMax;
        return this;
    }

    public int getAgeFilterMin() {
        return ageFilterMin;
    }

    public Profile setAgeFilterMin(int ageFilterMin) {
        this.ageFilterMin = ageFilterMin;
        return this;
    }

    public String getBio() {
        return bio;
    }

    public Profile setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Profile setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getBlend() {
        return blend;
    }

    public Profile setBlend(String blend) {
        this.blend = blend;
        return this;
    }

    public boolean isCanCreateSquad() {
        return canCreateSquad;
    }

    public Profile setCanCreateSquad(boolean canCreateSquad) {
        this.canCreateSquad = canCreateSquad;
        return this;
    }

    public int getConnectionCount() {
        return ConnectionCount;
    }

    public Profile setConnectionCount(int connectionCount) {
        ConnectionCount = connectionCount;
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Profile setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public boolean isDiscoverable() {
        return discoverable;
    }

    public Profile setDiscoverable(boolean discoverable) {
        this.discoverable = discoverable;
        return this;
    }

    public int getDistanceFilter() {
        return distanceFilter;
    }

    public Profile setDistanceFilter(int distanceFilter) {
        this.distanceFilter = distanceFilter;
        return this;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public Profile setFacebookId(String facebookId) {
        this.facebookId = facebookId;
        return this;
    }

    public int getGender() {
        return gender;
    }

    public Profile setGender(int gender) {
        this.gender = gender;
        return this;
    }

    public int getGenderFilter() {
        return genderFilter;
    }

    public Profile setGenderFilter(int genderFilter) {
        this.genderFilter = genderFilter;
        return this;
    }

    public String getName() {
        return name;
    }

    public Profile setName(String name) {
        this.name = name;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Profile setUsername(String username) {
        this.username = username;
        return this;
    }

    public Position getPosition() {
        return position;
    }

    public Profile setPosition(Position position) {
        this.position = position;
        return this;
    }

    public boolean isPhotoOptimizerEnabled() {
        return photoOptimizerEnabled;
    }

    public Profile setPhotoOptimizerEnabled(boolean photoOptimizerEnabled) {
        this.photoOptimizerEnabled = photoOptimizerEnabled;
        return this;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public Profile setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
        return this;
    }

    public static Profile Builder() {
        return new Profile();
    }
}

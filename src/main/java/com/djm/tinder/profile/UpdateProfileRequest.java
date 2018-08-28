package com.djm.tinder.profile;

import com.djm.tinder.http.request.HttpGetRequest;
import com.djm.tinder.http.request.HttpPostRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


public class UpdateProfileRequest implements HttpPostRequest {
    public static final String URI = "/profile";

    private String url;
    private final ProfileUpdate profile;

    public UpdateProfileRequest(String url, ProfileUpdate profile) {
        this.url = url;
        this.profile = profile;
    }

    public String getUrl() {
       return url;
    }

    public ProfileUpdate getProfile() {
        return profile;
    }

    @Override
    public String getBody() {
        try {
            ObjectWriter ow = new ObjectMapper().writer();
            String json = ow.writeValueAsString(getProfile());
            return json;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

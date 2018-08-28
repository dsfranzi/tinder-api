package com.djm.tinder.profile;

import com.djm.tinder.http.request.HttpPostRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


public class UpdatePositionRequest implements HttpPostRequest {
    public static final String URI = "/user/ping";

    private String url;
    private final PositionUpdate position;

    public UpdatePositionRequest(String url, PositionUpdate position) {
        this.url = url;
        this.position = position;
    }

    public String getUrl() {
        return url;
    }

    public PositionUpdate getPosition() {
        return position;
    }

    @Override
    public String getBody() {
        try {
            ObjectWriter ow = new ObjectMapper().writer();
            String json = ow.writeValueAsString(getPosition());
            return json;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

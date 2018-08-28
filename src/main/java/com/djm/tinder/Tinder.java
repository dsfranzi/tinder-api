package com.djm.tinder;

import com.djm.tinder.auth.AuthRequest;
import com.djm.tinder.auth.AuthResponse;
import com.djm.tinder.http.client.AuthenticatedHttpClient;
import com.djm.tinder.http.request.HttpPostRequest;
import com.djm.tinder.http.client.AnonymousHttpClient;
import com.djm.tinder.like.Like;
import com.djm.tinder.like.LikeRequest;
import com.djm.tinder.like.LikeResponse;
import com.djm.tinder.match.Match;
import com.djm.tinder.match.MatchRequest;
import com.djm.tinder.match.MatchResponse;
import com.djm.tinder.profile.*;
import com.djm.tinder.user.User;
import com.djm.tinder.recommendation.RecommendationRequest;
import com.djm.tinder.recommendation.RecommendationResponse;

import okhttp3.*;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * @author Diego Mariani
 * @since 05-2017
 */
public class Tinder {

    /**
     * Base url for the tinder apis
     */
    public static final String BASE_URL = "https://api.gotinder.com";

    /**
     * Tinder API token
     */
    private String tinderApiToken;

    /**
     * Non authenticated http client.
     */
    private static AnonymousHttpClient anonymousHttpClient;

    /**
     * Authenticated http client. Can performs http request to the private tinder api endpoints.
     */
    private AuthenticatedHttpClient authenticatedHttpClient;

    private Tinder(String tinderApiToken) {
        this.tinderApiToken = tinderApiToken;
    }

    /**
     * Build the Tinder client given the access token.
     *
     * @param tinderApiToken
     * @return Tinder
     * @throws Exception
     */
    public static Tinder fromAccessToken(String tinderApiToken) {
        return new Tinder(tinderApiToken);
    }

    /**
     * Returns a list of recommendations.
     *
     * @return recommendations
     * @throws Exception
     */
    public ArrayList<User> getRecommendations() throws Exception {
        RecommendationResponse recommendationResponse = new RecommendationResponse(
                getAuthenticatedHttpClient().get(
                        new RecommendationRequest(BASE_URL + RecommendationRequest.URI)
                )
        );
        return recommendationResponse.getRecommendations();
    }

    /**
     * Returns the user profile information and settings.
     *
     * @return Profile
     */
    public Profile getProfile() throws Exception {
        ProfileResponse profileResponse = new ProfileResponse(getAuthenticatedHttpClient().get(new ProfileRequest(BASE_URL + ProfileRequest.URI)));
        return profileResponse.getProfile();
    }

    public void setProfile(ProfileUpdate profile) throws Exception {
        getAuthenticatedHttpClient().post(new UpdateProfileRequest(BASE_URL + UpdateProfileRequest.URI, profile));
    }

    public void setPosition(PositionUpdate position) throws Exception {
        getAuthenticatedHttpClient().post(new UpdatePositionRequest(BASE_URL + UpdatePositionRequest.URI, position));
    }

    /**
     * Likes a given user and returns a Like object
     *
     * @param user
     * @return Like
     */
    public Like like(User user) throws Exception {
        LikeResponse likeResponse = new LikeResponse(
                getAuthenticatedHttpClient().get(
                        new LikeRequest(
                                BASE_URL + LikeRequest.URI,
                                user.getId(),
                                user.getContentHash(),
                                user.getsNumber()
                        )
                )
        );
        return likeResponse.getLike();
    }

    /**
     * Return my tinder matches available until now as an array list
     *
     * @return my tinder matches
     * @throws Exception
     */
    public ArrayList<Match> getMatches() throws Exception {
        MatchResponse matchResponse = new MatchResponse(
                getAuthenticatedHttpClient().post(new MatchRequest(BASE_URL + MatchRequest.URI))
        );
        return matchResponse.getMatches();
    }

    /**
     * Retrieve the tinder access token in order to query the tinder api, given the facebook access token.
     *
     * @param facebookAccessToken
     * @return accessToken
     * @throws Exception
     */
    public static String getAccessToken(String facebookAccessToken) throws Exception {
        HttpPostRequest request = new AuthRequest(BASE_URL + AuthRequest.URI, facebookAccessToken);
        AuthResponse authResponse = new AuthResponse(getAnonymousHttpClient().post(request));
        return authResponse.getToken();
    }

    private static AnonymousHttpClient getAnonymousHttpClient() {
        if (anonymousHttpClient == null) {
            anonymousHttpClient = new AnonymousHttpClient(new OkHttpClient());
        }
        return anonymousHttpClient;
    }

    private AuthenticatedHttpClient getAuthenticatedHttpClient() throws Exception {
        if (authenticatedHttpClient == null) {
            authenticatedHttpClient = new AuthenticatedHttpClient(getAnonymousHttpClient(), tinderApiToken);
        }
        return authenticatedHttpClient;
    }
}

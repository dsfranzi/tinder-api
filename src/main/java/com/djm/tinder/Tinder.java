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
import com.djm.tinder.profile.Profile;
import com.djm.tinder.profile.ProfileRequest;
import com.djm.tinder.profile.ProfileResponse;
import com.djm.tinder.profile.UpdateProfileRequest;
import com.djm.tinder.user.User;
import com.djm.tinder.recommendation.RecommendationRequest;
import com.djm.tinder.recommendation.RecommendationResponse;

import okhttp3.*;

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
     * Facebook access token to retrieve Tinder API token
     */
    private String facebookAccessToken;

    /**
     * Non authenticated http client.
     */
    private AnonymousHttpClient anonymousHttpClient;

    /**
     * Authenticated http client. Can performs http request to the private tinder api endpoints.
     */
    private AuthenticatedHttpClient authenticatedHttpClient;

    private Tinder(String facebookAccessToken) throws Exception {
        this.facebookAccessToken = facebookAccessToken;
    }

    /**
     * Build the Tinder client given the access token.
     *
     * @param facebookAccessToken
     * @return Tinder
     * @throws Exception
     */
    public static Tinder fromAccessToken(String facebookAccessToken) throws Exception {
        return new Tinder(facebookAccessToken);
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

    public void setProfile() throws Exception {
        getAuthenticatedHttpClient().get(new UpdateProfileRequest(BASE_URL + UpdateProfileRequest.URI))
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
    private String getAccessToken(String facebookAccessToken) throws Exception {
        HttpPostRequest request = new AuthRequest(BASE_URL + AuthRequest.URI, facebookAccessToken);
        AuthResponse authResponse = new AuthResponse(getAnonymousHttpClient().post(request));
        return authResponse.getToken();
    }

    private AnonymousHttpClient getAnonymousHttpClient() {
        if (anonymousHttpClient == null) {
            anonymousHttpClient = new AnonymousHttpClient(new OkHttpClient());
        }
        return anonymousHttpClient;
    }

    private AuthenticatedHttpClient getAuthenticatedHttpClient() throws Exception {
        if (authenticatedHttpClient == null) {
            authenticatedHttpClient = new AuthenticatedHttpClient(getAnonymousHttpClient(), getAccessToken(facebookAccessToken));
        }
        return authenticatedHttpClient;
    }
}

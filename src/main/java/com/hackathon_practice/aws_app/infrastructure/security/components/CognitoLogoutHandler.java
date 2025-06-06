package com.hackathon_practice.aws_app.infrastructure.security.components;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;

/**
 * Cognito has a custom logout url.
 * See more information <a href="https://docs.aws.amazon.com/cognito/latest/developerguide/logout-endpoint.html">here</a>.
 */
public class CognitoLogoutHandler extends SimpleUrlLogoutSuccessHandler {

    /**
     * The domain of your user pool.
     */
    private String domain = "https://nmdf89.auth.us-east-2.amazoncognito.com";

    /**
     * An allowed callback URL after logout.
     * Update this to your actual app logout redirect URL.
     */
    private String logoutRedirectUrl = "http://localhost:8080"; // adjust this to your actual app URL

    /**
     * The ID of your User Pool Client.
     */
    private String userPoolClientId = "6hgdsjm7e7t0igf7bp0oul5irl";

    /**
     * Here, we must implement the new logout URL request.
     */
    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        return UriComponentsBuilder
                .fromUri(URI.create(domain + "/logout"))
                .queryParam("client_id", userPoolClientId)
                .queryParam("logout_uri", logoutRedirectUrl)
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUriString();
    }
}

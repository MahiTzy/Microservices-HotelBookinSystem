package com.gateway.registry.Controller;

import com.gateway.registry.Models.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @GetMapping("/login")
    public ResponseEntity<AuthResponse> auth(@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient authorizedClient,
                                             @AuthenticationPrincipal OidcUser oauth2User, Model model) {
        logger.info("User email: " + oauth2User.getEmail());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUserId(oauth2User.getEmail());
        authResponse.setAccessToken(authorizedClient.getAccessToken().getTokenValue());
        authResponse.setRefreshToken(authorizedClient.getRefreshToken().getTokenValue());
        authResponse.setExpiresIn(authorizedClient.getAccessToken().getExpiresAt().getEpochSecond());
        List<String> roles = oauth2User.getAuthorities().stream().map(a -> a.getAuthority()).toList();
        authResponse.setRoles(roles);
        return ResponseEntity.ok(authResponse);
    }
}

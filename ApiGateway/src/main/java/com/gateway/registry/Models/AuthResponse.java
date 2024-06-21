package com.gateway.registry.Models;

import lombok.*;

import java.util.Collection;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String userId;
    private String accessToken;
    private String refreshToken;
    private long expiresIn;
    private Collection<String> roles;
}
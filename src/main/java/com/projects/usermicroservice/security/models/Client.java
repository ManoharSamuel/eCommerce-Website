package com.projects.usermicroservice.security.models;

import java.time.Instant;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "`client`")
@Getter
@Setter
public class Client {
    @Id
    private String id;
    private String clientId;
    private Instant clientIdIssuedAt;
    private String clientSecret;
    private Instant clientSecretExpiresAt;
    private String clientName;
    @Column(length = 1000, columnDefinition = "TEXT")
    private String clientAuthenticationMethods;
    @Column(length = 1000, columnDefinition = "TEXT")
    private String authorizationGrantTypes;
    @Column(length = 1000, columnDefinition = "TEXT")
    private String redirectUris;
    @Column(length = 1000, columnDefinition = "TEXT")
    private String postLogoutRedirectUris;
    @Column(length = 1000, columnDefinition = "TEXT")
    private String scopes;
    @Column(length = 2000, columnDefinition = "TEXT")
    private String clientSettings;
    @Column(length = 2000, columnDefinition = "TEXT")
    private String tokenSettings;

    
}
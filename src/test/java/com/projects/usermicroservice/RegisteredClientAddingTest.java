package com.projects.usermicroservice;

import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.test.annotation.Commit;

import java.util.UUID;

@SpringBootTest
public class RegisteredClientAddingTest {
    @Inject
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Inject
    private RegisteredClientRepository registeredClientRepository;

    @Test
   @Commit
    public void addRegisteredClient() {
//        RegisteredClient.Builder builder = RegisteredClient.withId(UUID.randomUUID().toString());
//        builder.clientId("productservice");
//        builder.clientSecret(bCryptPasswordEncoder.encode("productservicesecret"));
//        builder.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC);
//        builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
//        builder.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN);
//        RegisteredClient oidcClient = builder
//                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//               .redirectUri("http://127.0.0.1:8080/login/oauth2/code/oidc-client")
//               .redirectUri("https://oauth.pstmn.io/v1/callback")
//               .postLogoutRedirectUri("http://127.0.0.1:8080/")
//                .scope(OidcScopes.OPENID)
//               .scope(OidcScopes.PROFILE)
//               .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//               .build();
//
//       registeredClientRepository.save(oidcClient);
    }
}

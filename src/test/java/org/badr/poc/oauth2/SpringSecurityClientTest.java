package org.badr.poc.oauth2;

import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import java.util.List;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

class SpringSecurityClientTest {

    private static final Pattern uuidRegex = Pattern.compile("^[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}$");


    @Test
    void shouldGetToken() {
        // Given
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setAccessTokenUri("http://localhost:8080/v1/oauth/tokens");
        details.setClientId("test_client_1");
        details.setClientSecret("test_secret");
        details.setScope(List.of("read_write"));

        // And
        OAuth2RestOperations oAuth2RestOperations = new OAuth2RestTemplate(details);

        // When
        var token = oAuth2RestOperations.getAccessToken().getValue();

        // Then
        assertThat(token).matches(uuidRegex);
    }

}


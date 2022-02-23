package org.badr.poc.oauth2;

import org.dmfs.httpessentials.client.HttpRequestExecutor;
import org.dmfs.httpessentials.exceptions.ProtocolError;
import org.dmfs.httpessentials.exceptions.ProtocolException;
import org.dmfs.httpessentials.httpurlconnection.HttpUrlConnectionExecutor;
import org.dmfs.oauth2.client.BasicOAuth2AuthorizationProvider;
import org.dmfs.oauth2.client.BasicOAuth2Client;
import org.dmfs.oauth2.client.BasicOAuth2ClientCredentials;
import org.dmfs.oauth2.client.OAuth2AccessToken;
import org.dmfs.oauth2.client.OAuth2AuthorizationProvider;
import org.dmfs.oauth2.client.OAuth2Client;
import org.dmfs.oauth2.client.OAuth2ClientCredentials;
import org.dmfs.oauth2.client.grants.ClientCredentialsGrant;
import org.dmfs.oauth2.client.scope.BasicScope;
import org.dmfs.rfc5545.Duration;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

class OauthEssentialsClientTest {

    private static final Pattern uuidRegex = Pattern.compile("^[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}$");


    @Test
    void shouldGetToken() throws ProtocolException, IOException, ProtocolError {
        // Given
        HttpRequestExecutor executor = new HttpUrlConnectionExecutor();

        OAuth2AuthorizationProvider provider = new BasicOAuth2AuthorizationProvider(
                null,
                URI.create("http://localhost:8080/v1/oauth/tokens"),
                new Duration(1,0,3600) /* default expiration time in case the server doesn't return any */);

        OAuth2ClientCredentials credentials = new BasicOAuth2ClientCredentials(
                "test_client_1", "test_secret");

        // And
        OAuth2Client oAuth2Client = new BasicOAuth2Client(provider, credentials, (URI) null);

        // When
        OAuth2AccessToken token = new ClientCredentialsGrant(oAuth2Client, new BasicScope("read_write")).accessToken(executor);

        // Then
        assertThat(token.accessToken()).matches(uuidRegex);
    }

}
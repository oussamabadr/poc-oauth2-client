package org.badr.poc.oauth2;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

class ScribeJavaClientTest {

        private static final Pattern uuidRegex = Pattern.compile("^[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}$");


        @Test
        void shouldGetToken() throws IOException, ExecutionException, InterruptedException {
            // Given
            OAuth20Service oAuthService = new ServiceBuilder("test_client_1")
                    .apiSecret("test_secret")
                    .defaultScope("read_write")
                    .build(MockedApi.instance());

            // When
            OAuth2AccessToken oAuth2AccessToken = oAuthService.getAccessTokenClientCredentialsGrant();

            // Then
            assertThat(oAuth2AccessToken.getAccessToken()).matches(uuidRegex);
        }

        static class MockedApi extends DefaultApi20 {

            private static class InstanceHolder {
                private static final MockedApi INSTANCE = new MockedApi();
            }

            public static MockedApi instance() {
                return InstanceHolder.INSTANCE;
            }

            @Override
            public String getAccessTokenEndpoint() {
                return "http://localhost:8080/v1/oauth/tokens";
            }

            @Override
            protected String getAuthorizationBaseUrl() {
                return null;
            }
        }

}
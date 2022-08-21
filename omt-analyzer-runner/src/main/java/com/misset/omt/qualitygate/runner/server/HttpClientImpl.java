package com.misset.omt.qualitygate.runner.server;

import org.sonarsource.sonarlint.core.commons.http.HttpClient;
import org.sonarsource.sonarlint.core.commons.http.HttpConnectionListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * A HttpClient that is used by Sonarqube ServerApi
 */
public class HttpClientImpl implements HttpClient {

    private final String authHeader;

    public HttpClientImpl(String token) {
        authHeader = getBasicAuthenticationHeader(token);
    }

    private static String getBasicAuthenticationHeader(String token) {
        String valueToEncode = token + ":";
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }

    @Override
    public Response get(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .setHeader("Authorization", authHeader)
                    .GET()
                    .build();
            java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

            return new ServerResponse(response);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CompletableFuture<Response> getAsync(String url) {
        // not part of the runner
        return null;
    }

    @Override
    public AsyncRequest getEventStream(String url,
                                       HttpConnectionListener connectionListener,
                                       Consumer<String> messageConsumer) {
        // not part of the runner
        return null;
    }

    @Override
    public Response post(String url, String contentType, String body) {
        // not part of the runner
        return null;
    }

    @Override
    public Response delete(String url, String contentType, String body) {
        // not part of the runner
        return null;
    }
}

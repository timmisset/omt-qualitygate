package com.misset.omt.qualitygate.runner.server;

import org.apache.commons.io.IOUtils;
import org.sonarsource.sonarlint.core.commons.http.HttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ServerResponse implements HttpClient.Response {

    private final HttpResponse<InputStream> response;

    public ServerResponse(HttpResponse<InputStream> response) {
        this.response = response;
    }

    @Override
    public int code() {
        return response.statusCode();
    }

    @Override
    public String bodyAsString() {
        try {
            return IOUtils.toString(response.body(), StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public InputStream bodyAsStream() {
        return response.body();
    }

    @Override
    public void close() {
        try {
            response.body().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String url() {
        return response.uri().getPath();
    }
}

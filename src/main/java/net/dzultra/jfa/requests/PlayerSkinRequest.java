package net.dzultra.jfa.requests;

import net.dzultra.jfa.exceptions.InvalidUUIDException;
import net.dzultra.jfa.responses.PlayerSkinResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class PlayerSkinRequest extends Request<byte[]>{
    private final String uuid;
    private final String username;
    private final boolean asHead;

    private static final String baseEndpoint = "/skin?uuid=";

    public PlayerSkinRequest(String uuid, boolean asHead) {
        super(baseEndpoint, uuid);

        if (!data.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")) {
            throw new InvalidUUIDException(this);
        } else {
            this.uuid = uuid;
        }
        this.asHead = asHead;
        this.username = null;
    }

    public PlayerSkinRequest(String data, boolean asHead, boolean asUsername) {
        super(baseEndpoint, data);
        if (!asUsername) { // If asUsername is false, data is treated as UUID
            this.username = null;
            if (!data.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")) {
                throw new InvalidUUIDException(this);
            } else {
                this.uuid = data;
            }
        } else { // If asUsername is true, data is treated as username
            this.username = data;
            this.uuid = getUuidFromUsername(username);
        }
        this.asHead = asHead;
    }

    @Override
    public String getBaseEndpoint() {
        return baseEndpoint;
    }

    @Override
    public String getFullEndpoint() {
        return this.getBaseEndpoint() + this.getUuid() + (asHead ? "&format=head" : "");
    }

    @Override
    public String getUrl() {
        return "https://www.mcfoxcraft.com/api" + getFullEndpoint();
    }

    public String getUuid() {
        return this.uuid;
    }

    @Override
    public PlayerSkinResponse getResponse() {
        byte[] byteArrayResponse = getByteArrayResponse();
        return new PlayerSkinResponse(byteArrayResponse);
    }

    protected byte[] getByteArrayResponse() {
        // Check whether Error is present and then return null if so
        Optional<HttpResponse<String>> optionalStringResponse = this.getOptionalStringHttpResponse();
        String stringResponse = optionalStringResponse.map(HttpResponse::body).orElse(null);
        if (this.isValidResponse(stringResponse)) {
            System.out.println("No skin found for the given uuid.");
            return null;
        }

        Optional<HttpResponse<byte[]>> optionalByteArrayResponse = this.getOptionalByteArrayHttpResponse();
        // Return null if the response is not present
        return optionalByteArrayResponse.map(HttpResponse::body).orElse(null);
    }

    private Optional<HttpResponse<byte[]>> getOptionalByteArrayHttpResponse() {
        try {
            System.out.println("Sending PlayerSkinRequest to URL: " + this.getUrl());
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(this.getUrl()))
                    .GET()
                    .build();
            return Optional.of(this.getHttpClient().send(request, HttpResponse.BodyHandlers.ofByteArray()));
        } catch (IOException | InterruptedException | IllegalArgumentException e) {
            System.out.println("Error during PlayerSkinRequest HTTP call: " + e.getMessage());
            return Optional.empty();
        }
    }

    private Optional<HttpResponse<String>> getOptionalStringHttpResponse() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(this.getUrl()))
                    .GET()
                    .build();
            return Optional.of(this.getHttpClient().send(request, HttpResponse.BodyHandlers.ofString()));
        } catch (IOException | InterruptedException | IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}

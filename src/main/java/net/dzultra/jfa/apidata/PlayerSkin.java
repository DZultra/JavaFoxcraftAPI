package net.dzultra.jfa.apidata;

import net.dzultra.jfa.exceptions.InvalidResponseException;
import net.dzultra.jfa.requests.PlayerSkinRequest;
import net.dzultra.jfa.responses.PlayerSkinResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PlayerSkin extends APIDataObject<PlayerSkinRequest,PlayerSkinResponse>{
    private byte[] png;

    public PlayerSkin(String uuid, boolean asHead) {
        PlayerSkinRequest request = new PlayerSkinRequest(uuid, asHead);
        PlayerSkinResponse response = request.getResponse();
        super(request, response);
        dataHandler();
    }

    public PlayerSkin(String data, boolean asHead, boolean asUsername) {
        PlayerSkinRequest request = new PlayerSkinRequest(data, asHead, asUsername);
        PlayerSkinResponse response = request.getResponse();
        super(request, response);
        dataHandler();
    }

    public PlayerSkin(PlayerSkinRequest request) {
        PlayerSkinResponse response = request.getResponse();
        super(request, response);
        dataHandler();
    }

    @Override
    protected void dataHandler() {
        if (this.response.png() == null) throw new InvalidResponseException(this);
        this.png = this.response.png();
    }

    @Override
    public String getName() {
        return "PlayerSkin";
    }

    public byte[] getPng() {
        return this.png;
    }

    public PlayerSkin writeToPath(Path path) {
        try {
            Files.write(path, this.getPng());
        } catch (IOException e) {
            System.out.println("Failed to write skin to file: " + e.getMessage());
        }
        return this;
    }
}

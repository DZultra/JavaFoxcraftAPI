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
        this(new PlayerSkinRequest(uuid, asHead));
    }

    public PlayerSkin(String data, boolean asHead, boolean asUsername) {
        this(new PlayerSkinRequest(data, asHead, asUsername));
    }

    public PlayerSkin(PlayerSkinRequest request) {
        super(request, request.getResponse());
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

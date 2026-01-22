package net.dzultra.jfa.apidata;

import net.dzultra.jfa.requests.PlayerSkinRequest;
import net.dzultra.jfa.responses.PlayerSkinResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PlayerSkin extends APIDataObject<PlayerSkinResponse>{
    private final PlayerSkinResponse playerSkinResponse;
    private byte[] png;

    public PlayerSkin(String uuid, boolean asHead) {
        PlayerSkinResponse response = new PlayerSkinRequest(uuid, asHead).getResponse();
        super(response);
        this.playerSkinResponse = response;
        dataHandler();
    }

    public PlayerSkin(String data, boolean asHead, boolean asUsername) {
        PlayerSkinResponse response = new PlayerSkinRequest(data, asHead, asUsername).getResponse();
        super(response);
        this.playerSkinResponse = response;
        dataHandler();
    }

    public PlayerSkin(PlayerSkinResponse playerSkinResponse) {
        super(playerSkinResponse);
        this.playerSkinResponse = playerSkinResponse;
        dataHandler();
    }

    protected void dataHandler() {
        if (this.playerSkinResponse.png() == null) throw new IncompleteResponseException(this);
        this.png = this.playerSkinResponse.png();
    }

    public byte[] png() {
        return this.png;
    }

    public PlayerSkin writeToPath(Path path) {
        try {
            Files.write(path, this.png());
        } catch (IOException e) {
            System.out.println("Failed to write skin to file: " + e.getMessage());
        }
        return this;
    }
}

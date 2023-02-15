package onebeastchris.util;

import org.geysermc.cumulus.form.ModalForm;
import org.geysermc.cumulus.form.SimpleForm;
import org.geysermc.cumulus.util.FormImage;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;
import static onebeastchris.hereAmI.LOGGER;

import java.util.UUID;

public class Forms {
    public static void sendForm(UUID uuid){
        try{
            if (FloodgateUtil.isFloodgatePlayer(uuid)) {
                FloodgateApi.getInstance().sendForm(uuid, SimpleForm.builder()
                        .title("Title")
                        .content("Content")
                        .button("Button without an image")
                        .build());

                LOGGER.info("Player is a Bedrock player!");
            }
        LOGGER.info("Player " + uuid + " is a Java player!");
        } catch (Exception e) {
            LOGGER.error("Error sending form to " + uuid);
            LOGGER.error(e.toString());
            e.printStackTrace();
        }
    }
}
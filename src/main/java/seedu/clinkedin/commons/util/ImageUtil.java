package seedu.clinkedin.commons.util;

import javafx.scene.image.Image;
import seedu.clinkedin.MainApp;

/**
 * Retrieves Image file
 */
public class ImageUtil {

    private static final String IMAGE_RESOURCE_PATH = "/images/";

    private static Image getImage(String imagePath) {
        return new Image(MainApp.class.getResourceAsStream(imagePath));
    }

    /**
     * Returns a social platform icon given a platform.
     */
    public static Image getSocialIcon(String platform) {

        String imageFile;
        switch (platform) {
            case "instagram":
                imageFile = "instagram.png";
                break;
            case "telegram":
                imageFile = "telegram.png";
                break;
            case "facebook":
                imageFile = "facebook.png";
                break;
            case "github":
                imageFile = "github.png";
                break;
            case "discord":
                imageFile = "discord.png";
                break;
            case "linkedin":
                imageFile = "linkedin.png";
                break;
            case "snapchat":
                imageFile = "snapchat.png";
                break;
            case "twitter":
                imageFile = "twitter.png";
                break;
            case "general":
                imageFile = "general.png";
                break;
            default:
                return null;
        }
        return getImage(IMAGE_RESOURCE_PATH + "links/" + imageFile);
    }
}

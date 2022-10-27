package seedu.address.model.person;

import javafx.scene.image.Image;

import java.io.File;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Randomly choose an image for Person's avatar
 */
public class AvatarList {

    public static final Image STEVE_IMAGE = new Image(
            new File("src/main/resources/images/steve.png").toURI().toString()
    );

    public static final Image RANDOM_SKIN_1 = new Image(
            new File("src/main/resources/images/randomSkin1.png").toURI().toString()
    );

    public static final Image RANDOM_SKIN_2 = new Image(
            new File("src/main/resources/images/randomSkin2.png").toURI().toString()
    );

    public static final Image RANDOM_SKIN_3 = new Image(
            new File("src/main/resources/images/randomSkin3.png").toURI().toString()
    );

    public static final Image RANDOM_SKIN_4 = new Image(
            new File("src/main/resources/images/randomSkin4.png").toURI().toString()
    );

    public static final Image RANDOM_SKIN_5 = new Image(
            new File("src/main/resources/images/randomSkin5.png").toURI().toString()
    );

    public static final Random RANDOM = new Random();
    public final List<Image> avatarList;

    /**
     *
     */
    public AvatarList() {
        this.avatarList = new ArrayList<>();
        avatarList.add(STEVE_IMAGE);
        avatarList.add(RANDOM_SKIN_1);
        avatarList.add(RANDOM_SKIN_2);
        avatarList.add(RANDOM_SKIN_3);
        avatarList.add(RANDOM_SKIN_4);
        avatarList.add(RANDOM_SKIN_5);
    }
    public static Image getRandomImage() {
        AvatarList listOfAvatars = new AvatarList();
        int randomNum = RANDOM.nextInt(0, listOfAvatars.avatarList.size());
        return listOfAvatars.avatarList.get(randomNum);
    }
}

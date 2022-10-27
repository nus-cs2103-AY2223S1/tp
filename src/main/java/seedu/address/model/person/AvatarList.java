package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.image.Image;


/**
 * Randomly choose an image for Person's avatar
 */
public class AvatarList {

    public static final Random RANDOM = new Random();

    public final Image steveImage = new Image(getClass().getResource("/images/steve.png")
            .toString());

    public final Image randomSkin1 = new Image(getClass().getResource("/images/randomSkin1.png")
            .toString());

    public final Image randomSkin2 = new Image(getClass().getResource("/images/randomSkin2.png")
            .toString());

    public final Image randomSkin3 = new Image(getClass().getResource("/images/randomSkin3.png")
            .toString());

    public final Image randomSkin4 = new Image(getClass().getResource("/images/randomSkin4.png")
            .toString());

    public final Image randomSkin5 = new Image(getClass().getResource("/images/randomSkin5.png")
            .toString());
    public final List<Image> avatarList;

    /**
     *
     */
    public AvatarList() {
        this.avatarList = new ArrayList<>();
        avatarList.add(steveImage);
        avatarList.add(randomSkin1);
        avatarList.add(randomSkin2);
        avatarList.add(randomSkin3);
        avatarList.add(randomSkin4);
        avatarList.add(randomSkin5);
    }
    public static Image getRandomImage() {
        AvatarList listOfAvatars = new AvatarList();
        int randomNum = RANDOM.nextInt(listOfAvatars.avatarList.size());
        return listOfAvatars.avatarList.get(randomNum);
    }

    public static Image getImage(int i) {
        AvatarList listOfAvatars = new AvatarList();
        int sizeOfList = listOfAvatars.avatarList.size();
        return listOfAvatars.avatarList.get(i % sizeOfList);
    }
}

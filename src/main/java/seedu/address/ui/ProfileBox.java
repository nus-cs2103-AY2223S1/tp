package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;


public class ProfileBox extends UiPart<Region> {
    private static final String FXML = "ProfileBox.fxml";

    @FXML
    private HBox box;

    @FXML
    private ImageView dp;

    @FXML
    private VBox profileInfoContainer;



    public ProfileBox(String img, String name, String title, String github, String timezone) {
        super(FXML);
        Image defaultDp = new Image(this.getClass().getResourceAsStream("/images/" + img + ".png"));
        dp.setImage(defaultDp);
        ProfileInfoBox profileInfoBox = new ProfileInfoBox(name, title, github, timezone);
        VBox.setVgrow(profileInfoBox.getRoot(), Priority.ALWAYS);
        profileInfoContainer.getChildren().add(profileInfoBox.getRoot());
    }
}

package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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

    private Image defaultDp = new Image(this.getClass().getResourceAsStream("/images/default.png"));

    public ProfileBox() {
        super(FXML);
        dp.setImage(defaultDp);
    }
}

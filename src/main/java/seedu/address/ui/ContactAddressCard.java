package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class ContactAddressCard extends UiPart<Region> {
    private static final String FXML = "ContactAddressCard.fxml";

    @FXML
    private HBox box;

    @FXML
    private ImageView icon;

    public ContactAddressCard(String iconName) {
        super(FXML);
        Image img = new Image(this.getClass().getResourceAsStream("/images/" + iconName + ".png"));
        icon.setImage(img);
    }
}

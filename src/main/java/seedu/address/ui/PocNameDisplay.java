package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

import static java.util.Objects.requireNonNull;

/**
 * A ui for the point-of-contact name display under the "Point Of Contacts" section.
 */
public class PocNameDisplay extends UiPart<Region> {

    private static final String FXML = "PocNameDisplay.fxml";

    @FXML
    private Text pocNameDisplay;

    public PocNameDisplay() {
        super(FXML);
    }

    public void setPocName(String pocName) {
        requireNonNull(pocName);

        pocNameDisplay.setText(pocName);
    }
}

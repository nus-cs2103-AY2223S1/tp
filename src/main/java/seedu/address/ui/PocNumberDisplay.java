package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

/**
 * A ui for the point-of-contact number display under the "Point Of Contacts" section.
 */
public class PocNumberDisplay extends UiPart<Region> {

    private static final String FXML = "PocNumberDisplay.fxml";

    @FXML
    private Text pocNumberDisplay;

    public PocNumberDisplay() {
        super(FXML);
    }

    public void setPocNumber(String pocNumber) {
        requireNonNull(pocNumber);

        pocNumberDisplay.setText(pocNumber);
    }
}

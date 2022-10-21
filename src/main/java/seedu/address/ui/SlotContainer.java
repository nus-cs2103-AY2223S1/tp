package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * A UI Component that that constitutes a region of a calendar view.
 */
public abstract class SlotContainer extends UiPart<Region> {

    public static final double WIDTH_SCALING_FACTOR = 120; // Value chosen to fit within the screen

    @FXML
    protected HBox slotPane;

    /**
     * Constructor for a slot container that sets the width of a slot
     */
    public SlotContainer(String fxml) {
        super(fxml);
        slotPane.setPrefWidth(WIDTH_SCALING_FACTOR);
    }
}

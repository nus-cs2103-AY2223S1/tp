package seedu.address.ui.schedule;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.ui.UiPart;

/**
 * A UI Component that that constitutes a region of a calendar view.
 */
public abstract class SlotContainer extends UiPart<Region> {

    public static final double WIDTH_SCALING_FACTOR = 120; // Value chosen to fit within the screen
    public static final double PREFERED_HEIGHT = 80; // Value chosen to fit within the screen

    @FXML
    protected HBox slotPane;
    @FXML
    protected VBox slotPaneVBox;

    /**
     * Constructor for a slot container that sets the width of a slot
     */
    public SlotContainer(String fxml) {
        super(fxml);
        slotPane.setPrefWidth(WIDTH_SCALING_FACTOR);
        slotPane.setPrefHeight(PREFERED_HEIGHT);
    }

    public void setColor(String color) {
        slotPaneVBox.setStyle("-fx-font-size: 20; -fx-background-color: " + color + "; -fx-background-radius: 10px;");
    }

}

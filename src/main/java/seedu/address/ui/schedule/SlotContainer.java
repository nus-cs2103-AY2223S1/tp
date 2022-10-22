package seedu.address.ui.schedule;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import seedu.address.ui.UiPart;

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

    public void setColor(String color) {
        BackgroundFill backgroundFill = new BackgroundFill(Paint.valueOf(color), new CornerRadii(10), Insets.EMPTY);
        Background background = new Background(backgroundFill);
        slotPane.setBackground(background);
    }

}

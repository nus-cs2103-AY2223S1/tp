package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.iteration.Iteration;


/**
 * A UI component that displays information of an {@code Iteration}.
 */
public class IterationListItem extends UiPart<Region> {

    private static final String FXML = "IterationListItem.fxml";

    public final Iteration iteration;

    @FXML
    private VBox iterationListItem;
    @FXML
    private Label date;
    @FXML
    private Label description;
    @FXML
    private ImageView image;
    @FXML
    private Label feedback;


    /**
     * Creates a {@code IterationListItem} with the given {@code Iteration} and index to display.
     */
    public IterationListItem(Iteration iteration) {
        super(FXML);
        this.iteration = iteration;
        date.setText(iteration.getDate().date.toString());
        description.setText(iteration.getDescription().description);
        image.setImage(new Image(iteration.getImagePath().path));
        feedback.setText(iteration.getFeedback().feedback);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IterationListItem)) {
            return false;
        }

        // state check
        IterationListItem otherItem = (IterationListItem) other;
        return iteration.equals(otherItem.iteration);
    }
}

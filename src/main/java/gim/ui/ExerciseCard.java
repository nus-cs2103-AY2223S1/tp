package gim.ui;

import java.util.Comparator;

import gim.model.exercise.Exercise;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code Exercise}.
 */
public class ExerciseCard extends UiPart<Region> {

    private static final String FXML = "ExerciseListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Exercise exercise;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label weight;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ExerciseCode} with the given {@code Exercise} and index to display.
     */
    public ExerciseCard(Exercise exercise, int displayedIndex) {
        super(FXML);
        this.exercise = exercise;
        id.setText(displayedIndex + ". ");
        name.setText(exercise.getName().fullName);
        weight.setText(exercise.getWeight().value);
        address.setText(exercise.getAddress().value);
        email.setText(exercise.getEmail().value);
        exercise.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ExerciseCard)) {
            return false;
        }

        // state check
        ExerciseCard card = (ExerciseCard) other;
        return id.getText().equals(card.id.getText())
                && exercise.equals(card.exercise);
    }
}

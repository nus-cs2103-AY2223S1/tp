package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of {@code TuitionClass} assigned
 * to {@code Student} or {@code Tutor}.
 */
public class AssignedClass extends UiPart<Region> {
    private static final String FXML = "AssignedClass.fxml";
    @FXML
    protected Label name;

    /**
     * Creates a {@code AssignedClass} with the given name to display.
     */
    public AssignedClass(String className) {
        super(FXML);
        name.setText(className);
    }
}

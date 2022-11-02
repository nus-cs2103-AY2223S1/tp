package seedu.taassist.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component to replace the title of a {@code StudentCard}.
 */
public class StudentCardTitle extends UiPart<Region> {

    private static final String FXML = "StudentCardTitle.fxml";
    private static final String TITLE = "%d. %s";

    @FXML
    private HBox hBox;
    @FXML
    private Label title;
    @FXML
    private Label grade;

    /**
     * Creates a {@code StudentCardTitle} with the given {@code title}.
     */
    public StudentCardTitle(int index, String name) {
        super(FXML);
        this.title.setText(String.format(TITLE, index, name));
    }

    /**
     * Sets the grade of the student in the title.
     */
    public void setGrade(double grade) {
        this.grade.setText(String.valueOf(grade));
    }
}

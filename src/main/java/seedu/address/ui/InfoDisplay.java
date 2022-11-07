package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.util.ChartUtil;
import seedu.address.model.person.Person;
import seedu.address.model.person.position.Student;

/**
 * A ui for the details of a specified person displayed at the right panel of TAB.
 */
public class InfoDisplay extends UiPart<Region> {

    private static final String FXML = "InfoDisplay.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private Person person;

    private StackedBarChart<String, Number> chart;

    @FXML
    private Label name;
    @FXML
    private Label position;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label misc;
    @FXML
    private StackPane placeHolder;
    @FXML
    private Label address;
    @FXML
    private Label remark;

    public InfoDisplay() {
        super(FXML);
    }

    /**
     * Displays information of a specified person.
     */
    public void setInfo(Person person) {
        this.person = person;
        name.setText(person.getName().fullName);
        position.setText(person.getPosition().value);
        phone.setText("Phone: " + person.getPhone().value);
        email.setText("Email: " + person.getEmail().value);
        person.getTags().stream().sorted(Comparator.comparing(tag -> tag.tagName))
                        .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        misc.setText(person.getPosition().toShow());
        address.setText("Address: " + person.getAddress().value);
        remark.setText("Remarks: " + person.getRemark().value);
        if (person.getPosition() instanceof Student) {
            Student s = (Student) person.getPosition();
            StackedBarChart<String, Number> chart = ChartUtil.createBarChart("Results",
                    "Assignments", "Score", s.getAssignmentsAndGrade(),
                    s.getAssignmentsAndMaximumGrade());
            chart.setAnimated(false);
            this.chart = chart;
            placeHolder.getChildren().add(chart);
        } else {
            this.chart = null;
            placeHolder.getChildren().clear();
        }
    }

    /**
     * Displays a Graph in a new window.
     */
    public void displayGraph() {
        if (chart != null && name != null) {
            Stage stage = new Stage();
            stage.setTitle("Grades of " + name.getText());
            Group root = new Group(chart);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Clears any information being displayed.
     */
    public void clearInfo() {
        name.setText(null);
        position.setText(null);
        phone.setText(null);
        email.setText(null);
        tags.getChildren().clear();
        misc.setText(null);
        address.setText(null);
        remark.setText(null);
        placeHolder.getChildren().clear();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InfoDisplay)) {
            return false;
        }

        // state check
        InfoDisplay current = (InfoDisplay) other;
        return person.equals(current.person);
    }
}

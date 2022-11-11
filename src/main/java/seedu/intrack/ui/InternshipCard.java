package seedu.intrack.ui;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.TextFlow;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.internship.Task;

/**
 * An UI component that displays information of a {@code Internship}.
 */
public class InternshipCard extends UiPart<Region> {

    private static final String FXML = "InternshipListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Internship internship;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label position;
    @FXML
    private FlowPane status;
    @FXML
    private TextFlow website;
    @FXML
    private Label salary;
    @FXML
    private Label upcomingTask;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code InternshipCode} with the given {@code Internship} and index to display.
     */
    public InternshipCard(Internship internship, int displayedIndex) {
        super(FXML);

        this.internship = internship;
        id.setText(displayedIndex + ". ");
        name.setText(internship.getName().fullName);
        name.setWrapText(true);
        position.setText(internship.getPosition().positionName);

        Label lab = new Label(internship.getStatus().toString());
        PseudoClass rejected = PseudoClass.getPseudoClass("rejected");
        lab.pseudoClassStateChanged(rejected, (internship.getStatus().toString()).equals("Rejected"));
        PseudoClass offered = PseudoClass.getPseudoClass("offered");
        lab.pseudoClassStateChanged(offered, (internship.getStatus().toString()).equals("Offered"));
        status.getChildren().add(lab);

        List<Task> taskList = internship.getTasks().stream()
                .filter(task -> task.getTaskTime().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
        if (taskList.size() == 0) {
            upcomingTask.setText("No upcoming tasks");
        } else if (taskList.size() > 0) {
            upcomingTask.setText(taskList.get(0).toString());
            upcomingTask.setGraphic(new ImageView(new Image("images/calendar.png")));
        }

        salary.setText(internship.getSalary().toString());

        internship.getTags().stream()
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
        if (!(other instanceof InternshipCard)) {
            return false;
        }

        // state check
        InternshipCard card = (InternshipCard) other;
        return id.getText().equals(card.id.getText())
                && internship.equals(card.internship);
    }
}

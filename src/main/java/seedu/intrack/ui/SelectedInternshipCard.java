package seedu.intrack.ui;

import java.awt.Desktop;
import java.net.URI;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.TextFlow;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.internship.Task;

/**
 * An UI component that displays information of a {@code Internship}.
 */
public class SelectedInternshipCard extends UiPart<Region> {

    private static final String FXML = "SelectedInternshipCard.fxml";

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
    private Label name;
    @FXML
    private Label position;
    @FXML
    private FlowPane status;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private TextFlow website;
    @FXML
    private FlowPane tasks;
    @FXML
    private FlowPane tags;
    @FXML
    private Label remark;

    /**
     * Creates a {@code InternshipCode} with the given {@code Internship} to display.
     */
    public SelectedInternshipCard(Internship internship) {
        super(FXML);

        this.internship = internship;
        name.setText(internship.getName().fullName);
        position.setText(internship.getPosition().positionName);

        Label lab = new Label(internship.getStatus().toString());
        PseudoClass rejected = PseudoClass.getPseudoClass("rejected");
        lab.pseudoClassStateChanged(rejected, (internship.getStatus().toString()).equals("Rejected"));
        PseudoClass offered = PseudoClass.getPseudoClass("offered");
        lab.pseudoClassStateChanged(offered, (internship.getStatus().toString()).equals("Offered"));
        status.getChildren().add(lab);

        phone.setText(internship.getPhone().value);
        email.setText(internship.getEmail().value);

        Hyperlink hyperlink = new Hyperlink(internship.getWebsite().value);
        website.getChildren().add(hyperlink);
        hyperlink.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI(internship.getWebsite().value));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });

        tasks.setMaxWidth(0);
        AtomicInteger count = new AtomicInteger();
        internship.getTasks().stream()
                .forEach(task -> {
                    Label temp = new Label(count.incrementAndGet() + ". " + task.taskName
                            + " at " + task.taskTime.format(Task.FORMATTER));
                    tasks.getChildren().add(temp);
                });

        internship.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        remark.setText(internship.getRemark().value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SelectedInternshipCard // instanceof handles nulls
                && internship.equals(((SelectedInternshipCard) other).internship)); // state check
    }
}

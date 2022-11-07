package seedu.application.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.application.model.application.Application;

/**
 * A UI component that displays information of an {@code Application}.
 */
public class ApplicationCard extends UiPart<Region> {

    private static final String FXML = "ApplicationListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     */

    public final Application application;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label company;
    @FXML
    private Label contact;
    @FXML
    private Label position;
    @FXML
    private Label email;
    @FXML
    private Label date;
    @FXML
    private Label isArchived;
    @FXML
    private Label status;
    @FXML
    private FlowPane tags;

    /**
     * Creates an {@code ApplicationCard} with the given {@code Application} and index to display.
     */
    public ApplicationCard(Application application, int displayedIndex) {
        super(FXML);
        this.application = application;
        id.setText(displayedIndex + ". ");
        company.setText(application.getCompany().company);
        isArchived.setVisible(application.isArchived());
        setStatus();
        contact.setText(application.getContact().value);
        position.setText(application.getPosition().value);
        email.setText(application.getEmail().value);
        date.setText(application.getDate().toString());
        application.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    private void setStatus() {
        status.setText(application.getStatus().getValue());
        switch (application.getStatus()) {
        case INTERVIEW:
            status.setStyle("-fx-background-color: dodgerblue");
            break;
        case OFFERED:
            status.setStyle("-fx-background-color: green");
            break;
        case PENDING:
            status.setStyle("-fx-background-color: darkorange");
            break;
        case REJECTED:
            status.setStyle("-fx-background-color: firebrick");
            break;
        default: // should not happen
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ApplicationCard)) {
            return false;
        }

        // state check
        ApplicationCard card = (ApplicationCard) other;
        return id.getText().equals(card.id.getText())
                && application.equals(card.application);
    }
}

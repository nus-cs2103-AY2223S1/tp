package hobbylist.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import hobbylist.commons.core.LogsCenter;
import hobbylist.model.activity.Activity;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;

/**
 * Panel displaying the details of selected activity.
 */
public class SelectedActivityPanel extends UiPart<Region> {
    private static final String FXML = "SelectedActivityPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SelectedActivityPanel.class);

    @FXML
    private Label name;
    @FXML
    private Label description;
    @FXML
    private Label date;
    @FXML
    private FlowPane tags;
    @FXML
    private Label status;
    @FXML
    private Label rating;

    public SelectedActivityPanel() {
        super(FXML);
    }

    /**
     * Fills in the selected activity box with the details of the selected activity
     * @param selectedActivity
     */
    public void setActivityDetails(ObservableList<Activity> selectedActivity) {
        Activity selected = selectedActivity.get(0);
        name.setText(selected.getName().fullName);
        description.setText(selected.getDescription().value);
        description.setWrapText(true);
        date.setText(selected.getDate().get(0).toDisplayedString());
        if (!tags.getChildren().isEmpty()) {
            tags.getChildren().clear();
        }
        selected.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        tags.getChildren().forEach(child -> {
            Label tagLabel = (Label) child;
            tagLabel.setStyle("-fx-background-color: " + intToHexColor(tagLabel.getText()));
        });
        status.setText(selected.getStatus().toString());
        if (selected.getRating() > 0) {
            rating.setText(String.format("%d / 5", selected.getRating()));
        } else {
            rating.setText("-");
        }
    }

    /**
     * Fills in the selected activity box with no activity selected
     */
    public void setNoActivityDetails() {
        name.setText("NO ACTIVITY SELECTED");
        description.setText("-");
        date.setText("-");
        status.setText("-");
        rating.setText("-");
    }

    private static String intToHexColor(String tag) {
        return String.format("#%06X", (0xFFFFFF & tag.hashCode()));

    }
}

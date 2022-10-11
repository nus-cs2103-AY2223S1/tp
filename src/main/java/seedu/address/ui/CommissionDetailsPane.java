package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import seedu.address.commons.core.ObservableObject;
import seedu.address.model.commission.Commission;
import seedu.address.model.commission.Description;
import seedu.address.model.iteration.Iteration;

/**
 * An UI component that displays information about a {@code Commission}.
 */
public class CommissionDetailsPane extends UiPart<Region> {

    private static final String FXML = "CommissionDetailsPane.fxml";
    private static final Image MONEY_BAG_ICON = new Image("/images/money bag.png");
    private static final Image CALENDAR_ICON = new Image("/images/calendar dark.png");
    private static final Image PERSON_ICON = new Image("/images/person silhouette.png");
    private static final Color COMPLETED_COLOR = Color.rgb(50, 174, 70);
    private static final Color IN_PROGRESS_COLOR = Color.rgb(84, 141, 225);
    private static final Color NOT_STARTED_COLOR = Color.rgb(184, 184, 184);

    private final Commission commission;

    @FXML
    private HBox detailsPane;
    @FXML
    private Label title;
    @FXML
    private Label description;
    @FXML
    private ImageView feeIcon;
    @FXML
    private Label fee;
    @FXML
    private ImageView deadlineIcon;
    @FXML
    private Label deadline;
    @FXML
    private Circle completionStatusCircle;
    @FXML
    private Label completionStatus;
    @FXML
    private ImageView customerIcon;
    @FXML
    private Label customerName;
    @FXML
    private FlowPane tags;
    @FXML
    private ListView<Iteration> iterationListView;

    /**
     * Creates a {@code CommissionDetailsPane} with the given {@code Commission} and index to display.
     */
    public CommissionDetailsPane(ObservableObject<Commission> commission) {
        super(FXML);
        this.commission = commission.getValue();
        updateUI(this.commission);
        commission.addListener((observable, oldValue, newValue) -> updateUI(newValue));
        iterationListView.setCellFactory(listView -> new IterationListViewCell());
    }

    private void updateUI(Commission commission) {
        if (commission == null) {
            removeFieldIcons();
            resetAllFields();
        } else {
            setFieldIcons();
            updateFieldAttributeComponents(commission);
            updateProgressComponents(commission);
            updateTagComponents(commission);
            updateIterationList(commission);
        }
    }

    private void removeFieldIcons() {
        feeIcon.setImage(null);
        deadlineIcon.setImage(null);
        customerIcon.setImage(null);
    }

    private void resetAllFields() {
        title.setText("No commission selected");
        description.setText("");
        fee.setText("");
        deadline.setText("");
        completionStatusCircle.setFill(null);
        completionStatus.setText("");
        customerName.setText("");
        tags.getChildren().clear();
        iterationListView.setItems(null);
    }

    private void setFieldIcons() {
        feeIcon.setImage(MONEY_BAG_ICON);
        deadlineIcon.setImage(CALENDAR_ICON);
        customerIcon.setImage(PERSON_ICON);
    }

    private void updateFieldAttributeComponents(Commission commission) {
        title.setText(commission.getTitle().title);
        description.setText(commission.getDescription().orElse(Description.NO_DESCRIPTION).description);
        fee.setText("$" + String.format("%.2f", commission.getFee().fee));
        deadline.setText(commission.getDeadline().toString());
        customerName.setText(commission.getCustomer().getName().fullName);
    }

    private void updateProgressComponents(Commission commission) {
        if (commission == null) {
            completionStatusCircle.setFill(null);
            completionStatus.setText("");
        }

        Commission.CompletionStatusString completionStatusString = commission.getCompletionStatusString();
        completionStatus.setText(completionStatusString.toString());
        switch (completionStatusString) {
        case COMPLETED:
            completionStatusCircle.setFill(COMPLETED_COLOR);
            break;
        case IN_PROGRESS:
            completionStatusCircle.setFill(IN_PROGRESS_COLOR);
            break;
        default:
            completionStatusCircle.setFill(NOT_STARTED_COLOR);
        }
    }

    private void updateTagComponents(Commission commission) {
        tags.getChildren().clear();
        commission.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    private void updateIterationList(Commission commission) {
        iterationListView.setItems(commission.getIterationList());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Iteration} using a {@code IterationListItem}.
     */
    class IterationListViewCell extends ListCell<Iteration> {
        @Override
        protected void updateItem(Iteration iteration, boolean empty) {
            super.updateItem(iteration, empty);

            if (empty || iteration == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new IterationListItem(iteration, getIndex() + 1).getRoot());
                updateProgressComponents(commission);
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommissionDetailsPane)) {
            return false;
        }

        // state check
        CommissionDetailsPane otherPane = (CommissionDetailsPane) other;
        return title.getText().equals(otherPane.title.getText())
                && commission.equals(otherPane.commission);
    }
}

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
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import seedu.address.commons.core.ObservableObject;
import seedu.address.model.commission.Commission;
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
    private static final Paint EMPTY_STATE_TEXT_FILL = Paint.valueOf("#6F747E");
    private static final Paint DEFAULT_STATE_TEXT_FILL = Paint.valueOf("#E8E8E8");

    private final ObservableObject<Commission> commission;

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
        this.commission = commission;
        updateUI(commission.getValue());
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
        description.setText("No commission selected");
        description.setTextFill(EMPTY_STATE_TEXT_FILL);
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
        fee.setText("$" + String.format("%.2f", commission.getFee().fee));
        deadline.setText(commission.getDeadline().toString());
        customerName.setText(commission.getCustomer().getName().fullName);

        if (commission.getDescription().isEmpty()) {
            description.setText("No description found");
            description.setTextFill(EMPTY_STATE_TEXT_FILL);
        } else {
            description.setText(commission.getDescription().get().description);
            description.setTextFill(DEFAULT_STATE_TEXT_FILL);
        }
    }

    private void updateProgressComponents(Commission commission) {
        if (commission == null) {
            completionStatusCircle.setFill(null);
            completionStatus.setText("");
        } else {
            Commission.CompletionStatusString completionStatusString = commission.getCompletionStatusString();
            completionStatus.setText(completionStatusString.toString());

            switch (completionStatusString) {
            case COMPLETED:
                completionStatusCircle.setFill(COMPLETED_COLOR);
                break;
            case IN_PROGRESS:
                completionStatusCircle.setFill(IN_PROGRESS_COLOR);
                break;
            case NOT_STARTED:
                completionStatusCircle.setFill(NOT_STARTED_COLOR);
                break;
            default:
                throw new AssertionError("Unknown CompletionStringStatus for Commission " + commission);
            }
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
            }
            updateProgressComponents(commission.getValue());
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

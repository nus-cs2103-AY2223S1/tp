package seedu.travelr.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.travelr.model.SummaryVariables;
import seedu.travelr.model.trip.Trip;

/**
 * Represents a Summary Window.
 */
public class SummaryWindow extends UiPart<Stage> {
    private static final String FXML = "summaryWindow.fxml";

    private CompletedTripListPanel completedTripListPanel;
    private SummaryCard summaryCard;

    @FXML
    private StackPane completedTripListPanelPlaceholder;

    @FXML
    private StackPane summaryCardPlaceholder;

    /**
     * Creates a new SummaryWindow.
     *
     * @param root Stage to use as the root of the SummaryWindow.
     */
    public SummaryWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new SummaryWindow.
     */
    public SummaryWindow(ObservableList<Trip> tripList, SummaryVariables summaryVariables, Image completed) {
        this(new Stage());

        summaryCard = new SummaryCard(summaryVariables);
        summaryCardPlaceholder.getChildren().add(summaryCard.getRoot());

        completedTripListPanel = new CompletedTripListPanel(tripList, completed);
        completedTripListPanelPlaceholder.getChildren().add(completedTripListPanel.getRoot());

    }

    /**
     * Shows the summary window.
     */
    public void show() {
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the summary window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the summary window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the summary window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

}

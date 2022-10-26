package seedu.travelr.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SummaryWindow extends UiPart<Stage> {
    private static final String FXML = "summaryWindow.fxml";

    @FXML
    private HBox summaryPanelPlaceholder;

    @FXML
    private StackPane completedTripListPanelPlaceholder;

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
    public SummaryWindow() {
        this(new Stage());
    }

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

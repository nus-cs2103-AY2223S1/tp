package seedu.trackascholar.ui;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

/**
 * Controller for an alert window.
 */
public class AlertWindow {
    private boolean isConfirmed = false;

    /**
     * Returns official confirmation user reply and displays an alert window.
     *
     * @param  message prompted to user.
     */
    public boolean display(String message) {
        ButtonType closeButtonType = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType actionButtonType = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, message, closeButtonType, actionButtonType);
        // Add CSS ID
        Node closeButton = confirmationAlert.getDialogPane().lookupButton(closeButtonType);
        closeButton.setId("closeBtn");
        confirmationAlert.getDialogPane().getStylesheets().add("view/DarkTheme.css");
        confirmationAlert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        //@@author benjytan45678
        //Reused from https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Alert.html
        // with minor modifications
        confirmationAlert.showAndWait()
                .filter(response -> response.getButtonData() == ButtonBar.ButtonData.YES)
                .ifPresent(response ->
                        setIsConfirmed());
        return isConfirmed;
    }

    public void setIsConfirmed() {
        this.isConfirmed = true;
    }

}

package seedu.address.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Prompts an alert window
 */
public class AlertWindow {
    private boolean isConfirmed = false;

    /**
     * Returns official confirmation user reply and displays an alert window
     * @param  message prompted to user
     */
    public boolean display(String message) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.CLOSE, ButtonType.APPLY);
        confirmationAlert.getDialogPane().getStylesheets().add("view/DarkTheme.css");
        confirmationAlert.showAndWait()
                .filter(response -> response == ButtonType.APPLY)
                .ifPresent(response ->
                        setIsConfirmed());
        return isConfirmed;
    }

    public void setIsConfirmed() {
        this.isConfirmed = true;
    }

}

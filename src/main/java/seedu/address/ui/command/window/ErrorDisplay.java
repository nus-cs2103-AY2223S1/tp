package seedu.address.ui.command.window;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Component that displays error messages for command windows.
 */
class ErrorDisplay {
    @FXML
    private final HBox errorMessagePlaceholder;
    @FXML
    private final Label errorMessageLabel;

    ErrorDisplay(HBox errorMessagePlaceholder) {
        this.errorMessagePlaceholder = errorMessagePlaceholder;
        Label errorLabel = new Label();
        errorLabel.getStyleClass().add("commandWindowErrorMessage");
        errorLabel.setWrapText(true);
        errorLabel.setPrefWidth(688);
        errorMessageLabel = errorLabel;
    }

    void clearError() {
        errorMessageLabel.setText("");
        errorMessagePlaceholder.getChildren().clear();
    }

    void setError(String errorMessage) {
        clearError(); // clear previous error message
        errorMessageLabel.setText(errorMessage);
        errorMessagePlaceholder.getChildren().add(errorMessageLabel);
    }
}

package seedu.watson.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.watson.auth.AuthHandler;
import seedu.watson.commons.core.GuiSettings;
import seedu.watson.logic.Logic;

/**
 * Class that handles the login window.
 */
public class LoginWindow extends UiPart<Stage> {

    private static final String FXML = "Login.fxml";

    private Stage primaryStage;
    private Logic logic;

    private LoginErrorWindow loginErrorWindow;

    /**
     * Creates a {@code LoginWindow} with the given {@code Stage} and {@code Logic}.
     */
    public LoginWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);
        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;
        this.loginErrorWindow = new LoginErrorWindow();

        // Holder for text fields
        VBox vb = new VBox();
        HBox nameHolder = new HBox();
        HBox passwordHolder = new HBox();

        // Name field
        Label nameLabel = new Label("Name:");
        final TextField nameField = new TextField();
        nameHolder.getChildren().addAll(nameLabel, nameField);
        nameHolder.setAlignment(Pos.CENTER);

        // Password field
        Label passwordLabel = new Label("Password:");
        final TextField passwordField = new TextField();
        passwordHolder.getChildren().addAll(passwordLabel, passwordField);
        passwordHolder.setAlignment(Pos.CENTER);

        // Submit button
        Button submit = new Button("Submit");

        // Textfield event handler
        // Setting an action for the Submit button
        submit.setOnAction(
            new EventHandler<ActionEvent>() {
                // Dynamically make an event handler for each button
                @Override
                public void handle(ActionEvent e) {
                    if ((nameField.getText() != null && !nameField.getText().isEmpty())
                        && (passwordField.getText() != null && !passwordField.getText().isEmpty())) {
                        String nameText = nameField.getText();
                        String passwordText = passwordField.getText();
                        if (AuthHandler.checkCredentials(nameText, passwordText)) {
                            // If the credentials are correct, show the main window
                            primaryStage.hide();
                            MainWindow mainWindow = new MainWindow(primaryStage, logic);
                            mainWindow.show(); //This should be called before creating other UI parts
                            mainWindow.fillInnerParts();
                        } else {
                            // If the credentials are wrong, show an error message
                            if (!loginErrorWindow.isShowing()) {
                                loginErrorWindow.show();
                            } else {
                                loginErrorWindow.focus();
                            }
                        }
                    } else {
                        throw new IllegalArgumentException("Password cannot be empty");
                    }
                }
            }
        );

        // Combining all components
        vb.getChildren().addAll(nameHolder, passwordHolder, submit);
        vb.setSpacing(10);
        vb.setAlignment(Pos.CENTER);
        Scene scene1 = new Scene(vb, 400, 250);
        primaryStage.setScene(scene1);

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }
}

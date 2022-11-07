package jeryl.fyp.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * The UI component that is responsible for displaying a welcome message.
 */
public class WelcomeBox extends UiPart<Region> {

    private static final String FXML = "WelcomeBox.fxml";

    @FXML
    private Label welcomeLabel;

    /**
     * Creates a {@code WelcomeBox}.
     */
    public WelcomeBox() {
        super(FXML);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM YYYY");
        LocalDateTime today = LocalDateTime.now();
        welcomeLabel.setText("Welcome back! " + dateTimeFormatter.format(today));
    }
}

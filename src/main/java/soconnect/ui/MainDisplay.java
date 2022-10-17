package soconnect.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import soconnect.commons.core.LogsCenter;

/**
 * The Ui for displaying both the list of persons and todos.
 */
public class MainDisplay extends UiPart<Region> {
    private static final String FXML = "MainDisplay.fxml";

    private final Logger logger = LogsCenter.getLogger(MainDisplay.class);

    @FXML
    private HBox mainDisplay;

    /**
     * Constructs a {@code MainDisplay} with the given {@code PersonListPanel} and {@code TodoListPanel}.
     */
    public MainDisplay(PersonListPanel personListPanel, TodoListPanel todoListPanel) {
        super(FXML);
        HBox.setHgrow(personListPanel.getRoot(), Priority.ALWAYS);
        HBox.setHgrow(todoListPanel.getRoot(), Priority.ALWAYS);
        mainDisplay.getChildren().addAll(personListPanel.getRoot(), todoListPanel.getRoot());
    };

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MainDisplay)) {
            return false;
        }

        // state check
        MainDisplay display = (MainDisplay) other;
        return mainDisplay.equals(display.mainDisplay);
    }

}

package seedu.rc4hdb.ui;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A ui element that is displayed at the footer of the application, which shows the current working file path.
 */
public class CurrentWorkingFileFooter extends UiPart<Region> {

    private static final String FXML = "CurrentWorkingFileFooter.fxml";

    @FXML
    private Label currentWorkingFileLabel;
    private ObservableValue<Path> currentWorkingFilePath;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}.
     */
    public CurrentWorkingFileFooter(ObservableValue<Path> currentWorkingFilePath) {
        super(FXML);
        this.currentWorkingFilePath = currentWorkingFilePath;
        updateFilePath(null, null, currentWorkingFilePath.getValue());

        // Set up listener
        currentWorkingFilePath.addListener(this::updateFilePath);
    }

    /**
     * Updates the current working file path that is displayed by the {@code CurrentWorkingFileFooter} ui element.
     */
    public void updateFilePath(ObservableValue<? extends Path> observable, Path oldValue, Path newValue) {
        currentWorkingFileLabel.setText(Paths.get(".").resolve(newValue).toString());
    }

}

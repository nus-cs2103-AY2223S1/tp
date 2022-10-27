package seedu.clinkedin.ui;

import java.io.File;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import seedu.clinkedin.commons.core.LogsCenter;
import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.logic.parser.exceptions.ParseException;

/**
 * Window for allowing import of addressbook.
 */
public class ImportWindow extends UiPart<Stage> {
    public static final String CHOOSE_FILE = "Choose file";
    private static final Logger logger = LogsCenter.getLogger(ImportWindow.class);
    private static final String FXML = "ImportWindow.fxml";

    private FileChooser fileChooser;
    private MainWindow mainWindow;

    @FXML
    private Label chooseFile;

    @FXML
    private Label chosenFile;

    @FXML
    private Button selectButton;

    @FXML
    private Button importButton;


    /**
     * Creates a new ImportWindow.
     *
     * @param root Stage to use as the root of the ImportWindow.
     */
    public ImportWindow(Stage root, MainWindow mainWindow) {
        super(FXML, root);
        this.mainWindow = mainWindow;
        chooseFile.setText(CHOOSE_FILE);
        fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File");

    }

    /**
     * Creates a new ImportWindow.
     */
    public ImportWindow(MainWindow mainWindow) {
        this(new Stage(), mainWindow);
    }

    /**
     * Shows the Import Window.
     */
    public void show() {
        logger.info("Showing Import Window.");
        chosenFile.setText("");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the import window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the import window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the import window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Opens Directory Chooser to select file to import.
     */
    @FXML
    public void handleSelect() {
        File selectedFile = fileChooser.showOpenDialog(getRoot());
        if (selectedFile != null) {
            chosenFile.setText(selectedFile.getAbsolutePath());
        }
    }

    /**
     * Imports the addressbook from the specified file.
     */
    @FXML
    public void handleOnImport() {
        try {
            String filePath = chosenFile.getText();
            mainWindow.executeFromWindow("import path/" + filePath);
        } catch (CommandException | ParseException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(e.getMessage());
            chosenFile.setText("");
            a.show();
            return;
        }
        chosenFile.setText("");
        this.hide();
    }
}

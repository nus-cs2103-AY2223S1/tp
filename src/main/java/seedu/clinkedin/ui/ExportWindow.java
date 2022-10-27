package seedu.clinkedin.ui;

import java.io.File;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import seedu.clinkedin.commons.core.LogsCenter;
import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.logic.parser.exceptions.ParseException;

/**
 * Window for allowing export of addressbook.
 */
public class ExportWindow extends UiPart<Stage> {
    public static final String CHOOSE_PATH = "Choose Location";
    public static final String CHOOSE_NAME = "Name";
    private static final Logger logger = LogsCenter.getLogger(ExportWindow.class);
    private static final String FXML = "ExportWindow.fxml";

    private DirectoryChooser directoryChooser;
    private MainWindow mainWindow;


    @FXML
    private Label chooseLocation;

    @FXML
    private Label chosenLocation;

    @FXML
    private Button selectButton;

    @FXML
    private Label fileName;

    @FXML
    private TextField userEnteredFileName;

    @FXML
    private Button exportButton;

    @FXML
    private ComboBox<String> fileFormat;


    /**
     * Creates a new ExportWindow.
     *
     * @param root Stage to use as the root of the ExportWindow.
     */
    public ExportWindow(Stage root, MainWindow parent) {
        super(FXML, root);
        this.mainWindow = parent;
        chooseLocation.setText(CHOOSE_PATH);
        directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose Location");
        fileName.setText(CHOOSE_NAME);
    }

    /**
     * Creates a new ExportWindow.
     */
    public ExportWindow(MainWindow parent) {
        this(new Stage(), parent);
    }

    /**
     * Shows the Export Window.
     */
    public void show() {
        logger.info("Showing Export Window.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the export window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the export window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the export window.
     */
    public void focus() {
        getRoot().requestFocus();
    }


    /**
     * Opens Directory Chooser to select location of file to export.
     */
    @FXML
    public void handleSelect() {
        File selectedDirectory = directoryChooser.showDialog(getRoot());
        if (selectedDirectory != null) {
            chosenLocation.setText(selectedDirectory.getAbsolutePath());
        }
    }

    /**
     * Exports addressbook to specified location.
     */
    @FXML
    public void handleOnExport() {
        try {
            String filePath = null;
            if (chosenLocation.getText().trim().equals("")) {
                filePath = userEnteredFileName.getText() + fileFormat.getValue();
            } else {
                filePath = chosenLocation.getText() + "/" + userEnteredFileName.getText() + fileFormat.getValue();
            }
            if (userEnteredFileName.getText().trim().contains("/")) {
                throw new ParseException("File name cannot contain '/'. Try again!");
            }
            mainWindow.executeFromWindow("export path/" + filePath);
        } catch (CommandException | ParseException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(e.getMessage());
            chosenLocation.setText("");
            userEnteredFileName.clear();
            a.show();
            return;
        }
        chosenLocation.setText("");
        userEnteredFileName.clear();
        this.hide();
    }
}

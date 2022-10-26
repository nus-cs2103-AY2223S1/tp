package seedu.clinkedin.ui;

import static seedu.clinkedin.commons.util.FileUtil.exportToCsvFile;
import static seedu.clinkedin.logic.commands.ExportCommand.toCsvFormat;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import seedu.clinkedin.commons.core.LogsCenter;
import seedu.clinkedin.logic.Logic;
import seedu.clinkedin.logic.commands.ExportCommand;
import seedu.clinkedin.logic.commands.exceptions.CommandException;

/**
 * Window for allowing export of addressbook.
 */
public class ImportWindow extends UiPart<Stage> {
    public static final String CHOOSE_FILE = "Choose file";
    private static final Logger logger = LogsCenter.getLogger(ImportWindow.class);
    private static final String FXML = "ImportWindow.fxml";

    private Logic logic;
    private ResultDisplay resultDisplay;
    private DirectoryChooser directoryChooser;


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
     * @param root Stage to use as the root of the ExportWindow.
     */
    public ImportWindow(Stage root, Logic logic, ResultDisplay resultDisplay) {
        super(FXML, root);
        this.logic = logic;
        this.resultDisplay = resultDisplay;
        chooseFile.setText(CHOOSE_FILE);
        directoryChooser = new DirectoryChooser();

    }

    /**
     * Creates a new ImportWindow.
     */
    public ImportWindow(Logic logic, ResultDisplay resultDisplay) {
        this(new Stage(), logic, resultDisplay);
    }

    /**
     * Shows the Import Window.
     */
    public void show() {
        logger.info("Showing Import Window.");
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
        File selectedFile = directoryChooser.showDialog(getRoot());
        if (selectedFile != null) {
            chosenFile.setText(selectedFile.getAbsolutePath());
        }
    }

    /**
     * Imports the addressbook.
     * @throws CommandException If unable to import.
     */
    @FXML
    public void handleOnExport() throws CommandException {
//        String filePath = chosenFile.getText();
//        List<String[]> data = toCsvFormat(logic.getFilteredPersonList());
//
//        try {
//            exportToCsvFile(filePath, data);
//        } catch (IOException ioe) {
//            Alert a = new Alert(Alert.AlertType.ERROR);
//            a.setContentText("Invalid File Name! Please try again!");
//            chosenLocation.setText("");
//            userEnteredFileName.clear();
//            a.show();
//            return;
//        }
//        resultDisplay.setFeedbackToUser(String.format(ExportCommand.MESSAGE_SUCCESS, filePath));
//        logger.info("Result: " + String.format(ExportCommand.MESSAGE_SUCCESS, filePath));
//        chosenLocation.setText("");
//        userEnteredFileName.clear();
//        this.hide();
    }
}

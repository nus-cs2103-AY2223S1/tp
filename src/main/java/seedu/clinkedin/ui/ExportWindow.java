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
public class ExportWindow extends UiPart<Stage> {
    public static final String CHOOSE_PATH = "Choose Location";
    public static final String CHOOSE_NAME = "Name";
    private static final Logger logger = LogsCenter.getLogger(ExportWindow.class);
    private static final String FXML = "ExportWindow.fxml";

    private Logic logic;
    private ResultDisplay resultDisplay;
    private DirectoryChooser directoryChooser;


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


    /**
     * Creates a new ExportWindow.
     *
     * @param root Stage to use as the root of the ExportWindow.
     */
    public ExportWindow(Stage root, Logic logic, ResultDisplay resultDisplay) {
        super(FXML, root);
        this.logic = logic;
        this.resultDisplay = resultDisplay;
        chooseLocation.setText(CHOOSE_PATH);
        directoryChooser = new DirectoryChooser();
        fileName.setText(CHOOSE_NAME);

    }

    /**
     * Creates a new ExportWindow.
     */
    public ExportWindow(Logic logic, ResultDisplay resultDisplay) {
        this(new Stage(), logic, resultDisplay);
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
     * Exports the person list to the specified path.
     */
    @FXML
    private void handleUserInput() {
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
     * @throws CommandException If unable to export to location.
     */
    @FXML
    public void handleOnExport() throws CommandException {
        String filePath = chosenLocation.getText() + "/" + userEnteredFileName.getText() + ".csv";
        List<String[]> data = toCsvFormat(logic.getFilteredPersonList());

        try {
            exportToCsvFile(filePath, data);
        } catch (IOException ioe) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Invalid File Name! Please try again!");
            chosenLocation.setText("");
            userEnteredFileName.clear();
            a.show();
            return;
        }
        resultDisplay.setFeedbackToUser(String.format(ExportCommand.MESSAGE_SUCCESS, filePath));
        logger.info("Result: " + String.format(ExportCommand.MESSAGE_SUCCESS, filePath));
        chosenLocation.setText("");
        userEnteredFileName.clear();
        this.hide();
    }
}

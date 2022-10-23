package seedu.clinkedin.ui;

import static seedu.clinkedin.commons.util.FileUtil.exportToCsvFile;
import static seedu.clinkedin.logic.commands.ExportCommand.toCsvFormat;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import seedu.clinkedin.commons.core.LogsCenter;
import seedu.clinkedin.logic.Logic;
import seedu.clinkedin.logic.commands.exceptions.CommandException;

public class ExportWindow extends UiPart<Stage> {
    public static final String CHOOSE_PATH = "Choose Location";
    public static final String CHOOSE_NAME = "Name";
    private static final Logger logger = LogsCenter.getLogger(ExportWindow.class);
    private static final String FXML = "ExportWindow.fxml";

    private Logic logic;
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
    public ExportWindow(Stage root, Logic logic) {
        super(FXML, root);
        this.logic = logic;
        chooseLocation.setText(CHOOSE_PATH);
        directoryChooser = new DirectoryChooser();
        fileName.setText(CHOOSE_NAME);
    }

    /**
     * Creates a new ExportWindow.
     */
    public ExportWindow(Logic logic) {
        this(new Stage(), logic);
    }

    /**
     * Shows the Export Window.
     */
    public void show() {
        logger.fine("Showing export window.");
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
    @FXML
    public void handleSelect() {
        File selectedDirectory = directoryChooser.showDialog(getRoot());
        if (selectedDirectory != null) {
            chosenLocation.setText(selectedDirectory.getAbsolutePath());
        }
    }
    @FXML
    public void handleOnExport() throws CommandException {
        String filePath = chosenLocation.getText() + "/" + userEnteredFileName.getText() + ".csv";
        List<String[]> data = toCsvFormat(logic.getFilteredPersonList());

        try {
            exportToCsvFile(filePath, data);
        } catch (IOException ioe) {
            throw new CommandException(ioe.getMessage());
        }
        chosenLocation.setText("");
        userEnteredFileName.clear();
        this.hide();
    }
}

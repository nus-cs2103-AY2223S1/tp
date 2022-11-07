package seedu.workbook.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.workbook.commons.core.LogsCenter;
import seedu.workbook.ui.util.HelpUtil;
import seedu.workbook.ui.util.HelpUtil.Command;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {


    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Label addCommand;

    @FXML
    private Label clearCommand;

    @FXML
    private Label deleteCommand;

    @FXML
    private Label editCommand;

    @FXML
    private Label listCommand;

    @FXML
    private Label redoCommand;

    @FXML
    private Label undoCommand;

    @FXML
    private Label findCommand;

    @FXML
    private Label exitCommand;

    @FXML
    private Button copyButton;

    @FXML
    private Label urlMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);

        addCommand.setText(HelpUtil.getCommandExample(Command.ADD));
        editCommand.setText(HelpUtil.getCommandExample(Command.EDIT));
        deleteCommand.setText(HelpUtil.getCommandExample(Command.DELETE));
        clearCommand.setText(HelpUtil.getCommandExample(Command.CLEAR));
        listCommand.setText(HelpUtil.getCommandExample(Command.LIST));
        redoCommand.setText(HelpUtil.getCommandExample(Command.REDO));
        undoCommand.setText(HelpUtil.getCommandExample(Command.UNDO));
        findCommand.setText(HelpUtil.getCommandExample(Command.FIND));
        exitCommand.setText(HelpUtil.getCommandExample(Command.EXIT));
        urlMessage.setText(HelpUtil.getUrlMessage());
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(HelpUtil.getUserGuideUrl());
        clipboard.setContent(url);
    }
}

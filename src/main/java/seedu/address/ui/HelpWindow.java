package seedu.address.ui;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SuggestCommand;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-t10-4.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "User Guide:";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private Text desc;

    @FXML
    private Text param;

    @FXML
    private Text ex;

    @FXML
    private Scene scene;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        setChoiceBox();
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
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }

    private void setChoiceBox() {
        choiceBox.getItems().addAll("Add", "Delete", "Edit", "Find", "List", "Help", "Clear", "Suggest", "Exit");
        setTextAutoWrap();
        choiceBox.setOnAction(e -> {
            String command = choiceBox.getValue();
            switch (command) {
            case "Add":
                setHelpLabels(AddCommand.class);
                break;
            case "Delete":
                setHelpLabels(DeleteCommand.class);
                break;
            case "Edit":
                setHelpLabels(EditCommand.class);
                break;
            case "Find":
                setHelpLabels(FindCommand.class);
                break;
            case "List":
                setHelpLabels(ListCommand.class);
                break;
            case "Help":
                setHelpLabels(HelpCommand.class);
                break;
            case "Clear":
                setHelpLabels(ClearCommand.class);
                break;
            case "Suggest":
                setHelpLabels(SuggestCommand.class);
                break;
            case "Exit":
                setHelpLabels(ExitCommand.class);
                break;
            default:
            }
        });
        choiceBox.setValue("Add");
    }

    private void setHelpLabels(Class<? extends Command> commandClass) {
        try {
            Command commandInstance = commandClass.getDeclaredConstructor().newInstance();
            desc.setText(commandInstance.getDescription());
            param.setText(commandInstance.getParameter());
            ex.setText(commandInstance.getExample());
        } catch (InvocationTargetException | NoSuchMethodException
                 | InstantiationException | IllegalAccessException e) {
            logger.warning("Unable to set help labels");
            e.printStackTrace();
        }
    }

    private void setTextAutoWrap() {
        desc.wrappingWidthProperty().bind(scene.widthProperty().subtract(20));
        param.wrappingWidthProperty().bind(scene.widthProperty().subtract(20));
        ex.wrappingWidthProperty().bind(scene.widthProperty().subtract(20));
    }
}

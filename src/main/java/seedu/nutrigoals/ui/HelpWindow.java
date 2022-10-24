package seedu.nutrigoals.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.TreeMap;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import seedu.nutrigoals.commons.core.LogsCenter;
import seedu.nutrigoals.logic.commands.AddCommand;
import seedu.nutrigoals.logic.commands.ClearCommand;
import seedu.nutrigoals.logic.commands.DeleteCommand;
import seedu.nutrigoals.logic.commands.EditCommand;
import seedu.nutrigoals.logic.commands.ExitCommand;
import seedu.nutrigoals.logic.commands.FindCommand;
import seedu.nutrigoals.logic.commands.HelpCommand;
import seedu.nutrigoals.logic.commands.ListCommand;
import seedu.nutrigoals.logic.commands.LocateGymCommand;
import seedu.nutrigoals.logic.commands.ProfileCommand;
import seedu.nutrigoals.logic.commands.ReviewCommand;
import seedu.nutrigoals.logic.commands.SetupCommand;
import seedu.nutrigoals.logic.commands.SuggestCommand;
import seedu.nutrigoals.logic.commands.TargetCommand;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-t17-2.github.io/tp/UserGuide.html";
    public static final String WELCOME_MESSAGE = "Welcome to NutriGoals!\n";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private Accordion instructions;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(WELCOME_MESSAGE);
        TreeMap<String, String> commands = getCommands();
        commands.forEach((commandWord, usage) -> {
            Label commandUsage = new Label(usage);
            commandUsage.setWrapText(true);
            HBox usageBox = new HBox(commandUsage);
            usageBox.setAlignment(Pos.CENTER_LEFT);
            TitledPane pane = new TitledPane(commandWord, usageBox);
            instructions.getPanes().add(pane);
        });
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
     * Returns all the commands and its usage
     * @return A treemap containing all the commands and its usage
     */
    public TreeMap<String, String> getCommands() {
        String regex = ": ";
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put(AddCommand.COMMAND_WORD, AddCommand.MESSAGE_USAGE.split(regex, 2)[1]);
        treeMap.put(ClearCommand.COMMAND_WORD, ClearCommand.MESSAGE_USAGE.split(regex, 2)[1]);
        treeMap.put(DeleteCommand.COMMAND_WORD, DeleteCommand.MESSAGE_USAGE.split(regex, 2)[1]);
        treeMap.put(EditCommand.COMMAND_WORD, EditCommand.MESSAGE_USAGE.split(regex, 2)[1]);
        treeMap.put(ExitCommand.COMMAND_WORD, ExitCommand.MESSAGE_USAGE.split(regex, 2)[1]);
        treeMap.put(FindCommand.COMMAND_WORD, FindCommand.MESSAGE_USAGE.split(regex, 2)[1]);
        treeMap.put(HelpCommand.COMMAND_WORD, HelpCommand.MESSAGE_USAGE.split(regex, 2)[1]);
        treeMap.put(ListCommand.COMMAND_WORD, ListCommand.MESSAGE_USAGE.split(regex, 2)[1]);
        treeMap.put(LocateGymCommand.COMMAND_WORD, LocateGymCommand.MESSAGE_USAGE.split(regex, 2)[1]);
        treeMap.put(ProfileCommand.COMMAND_WORD, ProfileCommand.MESSAGE_USAGE.split(regex, 2)[1]);
        treeMap.put(ReviewCommand.COMMAND_WORD, ReviewCommand.MESSAGE_USAGE.split(regex, 2)[1]);
        treeMap.put(SetupCommand.COMMAND_WORD, SetupCommand.MESSAGE_USAGE.split(regex, 2)[1]);
        treeMap.put(SuggestCommand.COMMAND_WORD, SuggestCommand.MESSAGE_USAGE.split(regex, 2)[1]);
        treeMap.put(TargetCommand.COMMAND_WORD, TargetCommand.MESSAGE_USAGE.split(regex, 2)[1]);
        return treeMap;
    }

    @FXML
    private void openUrl() {
        try {
            Desktop.getDesktop().browse(new URI(USERGUIDE_URL));
        } catch (IOException | URISyntaxException e) {
            logger.info("Invalid URL");
        }
    }
}

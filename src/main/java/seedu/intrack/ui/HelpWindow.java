package seedu.intrack.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.intrack.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-t11-2.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: ";

    public static final String LIST_COMMAND_DESC = "List all internship applications: ";
    public static final String LIST_COMMAND = "list";

    public static final String EXIT_COMMAND_DESC = "Exit program: ";
    public static final String EXIT_COMMAND = "exit";

    public static final String CLEAR_COMMAND_DESC = "Clear all existing entries: ";
    public static final String CLEAR_COMMAND = "clear";

    public static final String ADD_COMMAND_DESC = "Add new internship application information: ";
    public static final String ADD_COMMAND = "add c/COMPANY p/POSITION e/EMAIL w/WEBSITE s/SALARY [t/TAG]...";

    public static final String DELETE_COMMAND_DESC = "Delete entry: ";
    public static final String DELETE_COMMAND = "delete INDEX";

    public static final String ADDTAG_COMMAND_DESC = "Add tag(s) to entry: ";
    public static final String ADDTAG_COMMAND = "addtag INDEX TAG [MORE_TAGS]...";

    public static final String DELETETAG_COMMAND_DESC = "Delete tag(s) from entry: ";
    public static final String DELETETAG_COMMAND = "deltag INDEX TAG [MORE_TAGS]...";

    public static final String FILTER_COMMAND_DESC = "Filter applications by status (o for offered, p for progress, "
        + "r for rejected): ";
    public static final String FILTER_COMMAND = "filter STATUS";

    public static final String FINDNAME_COMMAND_DESC = "Search applications by company: ";
    public static final String FINDNAME_COMMAND = "findc KEYWORD [MORE_KEYWORDS]...";

    public static final String FINDPOSITION_COMMAND_DESC = "Search applications by position: ";
    public static final String FINDPOSITION_COMMAND = "findp KEYWORD [MORE_KEYWORDS]...";

    public static final String FINDTAG_COMMAND_DESC = "Search applications by tags: ";
    public static final String FINDTAG_COMMAND = "findt KEYWORD [MORE_KEYWORDS]...";

    public static final String SORT_COMMAND_DESC = "Sort internship applications by SORT_TYPE (time or salary) and "
        + "SORT_ORDER (a for ascending or d for descending): ";
    public static final String SORT_COMMAND = "sort SORT_TYPE SORT_ORDER";

    public static final String STATS_COMMAND_DESC = "View statistics of internship applications: ";
    public static final String STATS_COMMAND = "stats";

    public static final String STATUS_COMMAND_DESC = "Update status (o for offered, p for progress, r for rejected) "
            + "of the internship application: ";
    public static final String STATUS_COMMAND = "status INDEX NEW_STATUS";

    public static final String SELECT_COMMAND_DESC = "Select an internship application: ";
    public static final String SELECT_COMMAND = "select INDEX";

    public static final String NOTIF = "The following commands all require that an internship application be selected "
            + "via the select command before they can be executed.";

    public static final String ADDTASK_COMMAND_DESC = "Add a task to the selected internship application: ";
    public static final String ADDTASK_COMMAND = "addtask TASK_NAME /at TASK_TIME (must be in dd-MM-yyyy HH:mm format)";

    public static final String DELETETASK_COMMAND_DESC = "Delete a task from the selected internship application: ";
    public static final String DELETETASK_COMMAND = "deltask TASK_INDEX";

    public static final String EDIT_COMMAND_DESC = "Edit selected internship application: ";
    public static final String EDIT_COMMAND = "edit [c/NEW_NAME] [p/NEW_POSITION] [e/NEW_EMAIL] [w/NEW_WEBSITE] "
        + "[s/NEW_SALARY] [t/NEW_TAG]...";

    public static final String MAIL_COMMAND_DESC = "Open the mail app to send an email to the email address of the "
        + "selected internship application: ";
    public static final String MAIL_COMMAND = "mail";

    public static final String REMARK_COMMAND_DESC = "Add a remark or edit the remark of the selected internship "
        + "application: ";
    public static final String REMARK_COMMAND = "remark r/[REMARK]";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private Label helpLink;

    @FXML
    private Label listCommandDesc;

    @FXML
    private Label listCommand;

    @FXML
    private Label exitCommandDesc;

    @FXML
    private Label exitCommand;

    @FXML
    private Label clearCommandDesc;

    @FXML
    private Label clearCommand;

    @FXML
    private Label addCommandDesc;

    @FXML
    private Label addCommand;

    @FXML
    private Label deleteCommandDesc;

    @FXML
    private Label deleteCommand;

    @FXML
    private Label addTagCommandDesc;

    @FXML
    private Label addTagCommand;

    @FXML
    private Label deleteTagCommandDesc;

    @FXML
    private Label deleteTagCommand;

    @FXML
    private Label filterCommandDesc;

    @FXML
    private Label filterCommand;

    @FXML
    private Label findNameCommandDesc;

    @FXML
    private Label findNameCommand;

    @FXML
    private Label findPositionCommandDesc;

    @FXML
    private Label findPositionCommand;

    @FXML
    private Label findTagCommandDesc;

    @FXML
    private Label findTagCommand;

    @FXML
    private Label sortCommandDesc;

    @FXML
    private Label sortCommand;

    @FXML
    private Label statsCommandDesc;

    @FXML
    private Label statsCommand;

    @FXML
    private Label statusCommandDesc;

    @FXML
    private Label statusCommand;

    @FXML
    private Label selectCommandDesc;

    @FXML
    private Label selectCommand;

    @FXML
    private Label notif;

    @FXML
    private Label addTaskCommandDesc;

    @FXML
    private Label addTaskCommand;

    @FXML
    private Label deleteTaskCommandDesc;

    @FXML
    private Label deleteTaskCommand;

    @FXML
    private Label editCommandDesc;

    @FXML
    private Label editCommand;

    @FXML
    private Label mailCommandDesc;

    @FXML
    private Label mailCommand;

    @FXML
    private Label remarkCommandDesc;

    @FXML
    private Label remarkCommand;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        helpLink.setText(USERGUIDE_URL);

        listCommandDesc.setText(LIST_COMMAND_DESC);
        listCommand.setText(LIST_COMMAND);

        exitCommandDesc.setText(EXIT_COMMAND_DESC);
        exitCommand.setText(EXIT_COMMAND);

        clearCommandDesc.setText(CLEAR_COMMAND_DESC);
        clearCommand.setText(CLEAR_COMMAND);

        addCommandDesc.setText(ADD_COMMAND_DESC);
        addCommand.setText(ADD_COMMAND);

        deleteCommandDesc.setText(DELETE_COMMAND_DESC);
        deleteCommand.setText(DELETE_COMMAND);

        addTagCommandDesc.setText(ADDTAG_COMMAND_DESC);
        addTagCommand.setText(ADDTAG_COMMAND);

        deleteTagCommandDesc.setText(DELETETAG_COMMAND_DESC);
        deleteTagCommand.setText(DELETETAG_COMMAND);

        filterCommandDesc.setText(FILTER_COMMAND_DESC);
        filterCommand.setText(FILTER_COMMAND);

        findNameCommandDesc.setText(FINDNAME_COMMAND_DESC);
        findNameCommand.setText(FINDNAME_COMMAND);

        findPositionCommandDesc.setText(FINDPOSITION_COMMAND_DESC);
        findPositionCommand.setText(FINDPOSITION_COMMAND);

        findTagCommandDesc.setText(FINDTAG_COMMAND_DESC);
        findTagCommand.setText(FINDTAG_COMMAND);

        sortCommandDesc.setText(SORT_COMMAND_DESC);
        sortCommand.setText(SORT_COMMAND);

        statsCommandDesc.setText(STATS_COMMAND_DESC);
        statsCommand.setText(STATS_COMMAND);

        statusCommandDesc.setText(STATUS_COMMAND_DESC);
        statusCommand.setText(STATUS_COMMAND);

        selectCommandDesc.setText(SELECT_COMMAND_DESC);
        selectCommand.setText(SELECT_COMMAND);

        notif.setText(NOTIF);

        addTaskCommandDesc.setText(ADDTASK_COMMAND_DESC);
        addTaskCommand.setText(ADDTASK_COMMAND);

        deleteTaskCommandDesc.setText(DELETETASK_COMMAND_DESC);
        deleteTaskCommand.setText(DELETETASK_COMMAND);

        editCommandDesc.setText(EDIT_COMMAND_DESC);
        editCommand.setText(EDIT_COMMAND);

        mailCommandDesc.setText(MAIL_COMMAND_DESC);
        mailCommand.setText(MAIL_COMMAND);

        remarkCommandDesc.setText(REMARK_COMMAND_DESC);
        remarkCommand.setText(REMARK_COMMAND);
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
}

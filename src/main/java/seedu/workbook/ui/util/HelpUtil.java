package seedu.workbook.ui.util;

/**
 * Contains utility methods used in implementing methods regarding {@code HelpWindow} for GUI.
 */
public class HelpUtil {

    public static final String ADD_COMMAND = "add c/COMPANY r/ROLE s/STAGE [d/DATETIME] [e/EMAIL] "
            + "[l/PROGRAMMING LANGUAGE]… [t/TAG]…";
    public static final String EDIT_COMMAND = "edit INDEX [c/COMPANY] [r/ROLE] [s/STAGE] [d/DATETIME] [e/EMAIL] "
            + "[l/PROGRAMMING LANGUAGE]… [t/TAG]…";
    public static final String DELETE_COMMAND = "delete INDEX";
    public static final String CLEAR_COMMAND = "clear";
    public static final String LIST_COMMAND = "list";
    public static final String FIND_COMMAND = "find  c/COMPANY | r/ROLE | s/STAGE";
    public static final String UNDO_COMMAND = "undo";
    public static final String REDO_COMMAND = "redo";
    public static final String EXIT_COMMAND = "exit";
    public static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-t10-3.github.io/tp/UserGuide.html";
    public static final String URL_MESSAGE = "Refer to the user guide for more information: " + USERGUIDE_URL;

    /*
     * Contains all {@code commands} supported by WB.
     */
    public enum Command {
        ADD, EDIT, DELETE, CLEAR, LIST, FIND, UNDO, REDO, EXIT,
    }
     
    /**
     * Returns command example for a given command.
     * @param command Command to return example for.
     * @return Command example as String.
     */
    public static String getCommandExample(Command command) {
        switch(command) {
            case ADD:
                return ADD_COMMAND;
            case EDIT:
                return EDIT_COMMAND;
            case DELETE:
                return DELETE_COMMAND;
            case CLEAR:
                return CLEAR_COMMAND;
            case LIST:
                return LIST_COMMAND;
            case FIND:
                return FIND_COMMAND;
            case UNDO:
                return UNDO_COMMAND;
            case REDO:
                return REDO_COMMAND;
            case EXIT:
                return EDIT_COMMAND;
            default:
                return "Command Example Here.";
        }
    }

    public static String getUserGuideUrl() {
        return USERGUIDE_URL;
    }

    public static String getUrlMessage() {
        return URL_MESSAGE;
    }

}

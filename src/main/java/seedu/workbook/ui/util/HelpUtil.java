package seedu.workbook.ui.util;

/**
 * Contains utility methods used in implementing methods regarding {@code HelpWindow} for GUI.
 */
public class HelpUtil {

    public static final String ADD_HEADER = "Add an Internship: ";
    public static final String ADD_EXAMPLE = "add c/COMPANY r/ROLE s/STAGE [d/DATETIME] [e/EMAIL] "
            + "[l/PROGRAMMING LANGUAGE]… [t/TAG]…";
    public static final String EDIT_HEADER = "Edit an Internship: ";
    public static final String EDIT_EXAMPLE = "edit INDEX [c/COMPANY] [r/ROLE] [s/STAGE] [d/DATETIME] [e/EMAIL] "
            + "[l/PROGRAMMING LANGUAGE]… [t/TAG]…";
    public static final String DELETE_HEADER = "Delete an Internship: ";
    public static final String DELETE_EXAMPLE = "delete INDEX";
    public static final String CLEAR_HEADER = "Clear all Internships: ";
    public static final String CLEAR_EXAMPLE = "clear";
    public static final String LIST_HEADER = "List all Internships: ";
    public static final String LIST_EXAMPLE = "list";
    public static final String FIND_HEADER = "Filter Internships: ";
    public static final String FIND_EXAMPLE = "find  c/COMPANY | r/ROLE | s/STAGE";
    public static final String UNDO_HEADER = "Undo a command: ";
    public static final String UNDO_EXAMPLE = "undo";
    public static final String REDO_HEADER = "Redo a command: ";
    public static final String REDO_EXAMPLE= "redo";
    public static final String EXIT_HEADER = "Exit application: ";
    public static final String EXIT_EXAMPLE = "exit";
    public static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-t10-3.github.io/tp/UserGuide.html";
    public static final String URL_MESSAGE = "Refer to the user guide for more information: " + USERGUIDE_URL;

    /**
     * Contains all commands supported by WB.
     */
    public enum Command {
        ADD, EDIT, DELETE, CLEAR, LIST, FIND, UNDO, REDO, EXIT,
    }
     
    /**
     * Returns command header for a given command.
     * @param command Command to return header for.
     * @return Command header as String.
     */
    public static String getCommandHeader(Command command) {
        switch(command) {
            case ADD:
                return ADD_HEADER;
            case EDIT:
                return EDIT_HEADER;
            case DELETE:
                return DELETE_HEADER;
            case CLEAR:
                return CLEAR_HEADER;
            case LIST:
                return LIST_HEADER;
            case FIND:
                return FIND_HEADER;
            case UNDO:
                return UNDO_HEADER;
            case REDO:
                return REDO_HEADER;
            case EXIT:
                return EDIT_HEADER;
            default:
                return "Command Header Here.";
        }
    }

    /**
     * Returns command example for a given command.
     * @param command Command to return example for.
     * @return Command example as String.
     */
    public static String getCommandExample(Command command) {
        switch(command) {
            case ADD:
                return ADD_EXAMPLE;
            case EDIT:
                return EDIT_EXAMPLE;
            case DELETE:
                return DELETE_EXAMPLE;
            case CLEAR:
                return CLEAR_EXAMPLE;
            case LIST:
                return LIST_EXAMPLE;
            case FIND:
                return FIND_EXAMPLE;
            case UNDO:
                return UNDO_EXAMPLE;
            case REDO:
                return REDO_EXAMPLE;
            case EXIT:
                return EDIT_EXAMPLE;
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

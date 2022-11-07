package seedu.workbook.ui.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

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
    public static final String REDO_EXAMPLE = "redo";
    public static final String EXIT_HEADER = "Exit application: ";
    public static final String EXIT_EXAMPLE = "exit";
    public static final String USERGUIDE_URL = "https://ay2223s1-cs2103t-t10-3.github.io/tp/UserGuide.html";
    public static final String URL_MESSAGE = "Refer to the user guide for more information: " + USERGUIDE_URL;

    public static final List<String> listOfHeaders = Arrays.asList(
            ADD_HEADER,
            LIST_HEADER,
            FIND_HEADER,
            EDIT_HEADER,
            DELETE_HEADER,
            CLEAR_HEADER,
            UNDO_HEADER,
            REDO_HEADER,
            EXIT_HEADER
    );

    public static final List<String> listOfExamples = Arrays.asList(
            ADD_EXAMPLE,
            LIST_EXAMPLE,
            FIND_EXAMPLE,
            EDIT_EXAMPLE,
            DELETE_EXAMPLE,
            CLEAR_EXAMPLE,
            UNDO_EXAMPLE,
            REDO_EXAMPLE,
            EXIT_EXAMPLE  
    );

    public static final Map<Command, String> headerForCommands = new HashMap<>();
    public static final Map<Command, String> exampleForCommands = new HashMap<>();

    /**
     * Contains all commands supported by WB.
     */
    public enum Command {
        ADD, LIST, FIND, EDIT, DELETE, CLEAR, UNDO, REDO, EXIT,
    }

    /*
     * Static initialization block to populate the maps.
     * Each command is mapped to a header and example.
     */
    static {
        for (int i = 0; i < Command.values().length; i++) {
            headerForCommands.put(Command.values()[i], listOfHeaders.get(i));
            exampleForCommands.put(Command.values()[i], listOfExamples.get(i));
        }
    }

    /**
     * Returns command header for a given command.
     * @param command Command to return header for.
     * @return Command header as String.
     */
    public static String getCommandHeader(Command command) {
        return headerForCommands.get(command);
    }

    /**
     * Returns command example for a given command.
     * @param command Command to return example for.
     * @return Command example as String.
     */
    public static String getCommandExample(Command command) {
        return exampleForCommands.get(command);
    }

    public static String getUserGuideUrl() {
        return USERGUIDE_URL;
    }

    public static String getUrlMessage() {
        return URL_MESSAGE;
    }

}

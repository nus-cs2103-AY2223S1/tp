package coydir.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Format and usage for a Coydir command.
 * Used in {@code HelpWindow} for the help command.
 */
public class CommandFormat {

    // All profile commands below
    private static final CommandFormat ADD_COMMAND_FORMAT = new CommandFormat(
            "add",
            "Adds a person to Coydir.",
            "add n/NAME j/POSITION d/DEPARTMENT [p/PHONE] [e/EMAIL] [a/ADDRESS] [l/LEAVE] [t/TAG]…");

    private static final CommandFormat EDIT_COMMAND_FORMAT = new CommandFormat(
            "edit",
            "Edits an employee's details.",
            "edit INDEX [n/NAME] [j/POSITION] [d/DEPARTMENT] [p/PHONE] [e/EMAIL] [a/ADDRESS] [l/LEAVE] [t/TAG]…");

    private static final CommandFormat DELETE_COMMAND_FORMAT = new CommandFormat(
            "delete",
            "Deletes the specified person from Coydir, given the Employee ID.",
            "delete EMPLOYEE_ID");

    private static final CommandFormat BATCH_ADD_COMMAND_FORMAT = new CommandFormat(
            "batch-add",
            "Adds multiple employees all at once.",
            "batch-add FILENAME");

    private static final CommandFormat VIEW_COMMAND_FORMAT = new CommandFormat(
            "view",
            "View the details of an existing person in Coydir.",
            "view INDEX");

    private static final CommandFormat LIST_COMMAND_FORMAT = new CommandFormat(
            "list",
            "Shows a list of all persons in the company.",
            "list");

    private static final CommandFormat FIND_COMMAND_FORMAT = new CommandFormat(
            "find",
            "Find employees with a search filter.",
            "find [n/NAME_KEYWORD] [j/POSITION_KEYWORD] [d/DEPARTMENT_KEYWORD]");

    // All advanced commands below
    private static final CommandFormat ADD_LEAVE_COMMAND_FORMAT = new CommandFormat(
            "add-leave",
            "Adds a leave period for an employee.",
            "add-leave id/ID sd/START_DATE ed/END_DATE");

    private static final CommandFormat DELETE_LEAVE_COMMAND_FORMAT = new CommandFormat(
            "delete-leave",
            "Deletes a leave period for an employee.",
            "delete-leave id/ID i/INDEX");

    private static final CommandFormat RATE_COMMAND_FORMAT = new CommandFormat(
            "rate",
            "Rate the performance of an employee.",
            "rate id/ID r/RATING");

    private static final CommandFormat VIEW_DEPARTMENT_COMMAND_FORMAT = new CommandFormat(
            "view-department",
            "View the summarized details of a department given the name of the department.",
            "view-department DEPARTMENT");

    // All misc commands below
    private static final CommandFormat HELP_COMMAND_FORMAT = new CommandFormat(
            "help",
            "Opens the help window.",
            "help");

    private static final CommandFormat EXIT_COMMAND_FORMAT = new CommandFormat(
            "exit",
            "Exits the program.",
            "exit");

    private static final CommandFormat CLEAR_COMMAND_FORMAT = new CommandFormat(
            "clear",
            "Clears the data stored in the database.",
            "clear");

    private SimpleStringProperty command;
    private SimpleStringProperty usage;

    /**
     * Creates a {@code CommandFormat} object with a specified command and corresponding description, format.
     *
     * @param command The specified command.
     * @param description The corresponding description.
     * @param format The corresponding format.
     */
    public CommandFormat(String command, String description, String format) {
        this.command = new SimpleStringProperty(command);
        this.usage = new SimpleStringProperty(String.format("%s\nFormat: %s", description, format));
    }

    public static ObservableList<CommandFormat> getProfileCommands() {
        return FXCollections.observableArrayList(
                ADD_COMMAND_FORMAT,
                EDIT_COMMAND_FORMAT,
                DELETE_COMMAND_FORMAT,
                BATCH_ADD_COMMAND_FORMAT,
                VIEW_COMMAND_FORMAT,
                LIST_COMMAND_FORMAT,
                FIND_COMMAND_FORMAT
                );
    }

    public static ObservableList<CommandFormat> getAdvancedCommands() {
        return FXCollections.observableArrayList(
                ADD_LEAVE_COMMAND_FORMAT,
                DELETE_LEAVE_COMMAND_FORMAT,
                RATE_COMMAND_FORMAT,
                VIEW_DEPARTMENT_COMMAND_FORMAT
                );
    }

    public static ObservableList<CommandFormat> getMiscCommands() {
        return FXCollections.observableArrayList(
                HELP_COMMAND_FORMAT,
                EXIT_COMMAND_FORMAT,
                CLEAR_COMMAND_FORMAT
                );
    }

    public String getCommand() {
        return this.command.get();
    }

    public String getUsage() {
        return this.usage.get();
    }

}

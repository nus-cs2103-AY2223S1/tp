package coydir.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Format and usage for a Coydir command.
 * Used in {@code HelpWindow} for the help command.
 */
public class CommandFormat {
    private SimpleStringProperty command;
    private SimpleStringProperty usage;

    // All basic commands below
    private static final CommandFormat ADD_COMMAND_FORMAT = new CommandFormat(
            "add",
            "Adds a person to Coydir.",
            "add n/NAME j/POSITION [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]");

    private static final CommandFormat LIST_COMMAND_FORMAT = new CommandFormat(
            "list",
            "Shows a list of all persons in the company.",
            "list");

    private static final CommandFormat VIEW_COMMAND_FORMAT = new CommandFormat(
            "view",
            "View the details of an existing person in Coydir.",
            "view INDEX");

    private static final CommandFormat DELETE_COMMAND_FORMAT = new CommandFormat(
            "delete",
            "Deletes the specified person from Coydir, given the Employee ID.",
            "delete EMPLOYEE_ID");

    private static final CommandFormat EXIT_COMMAND_FORMAT = new CommandFormat(
            "exit",
            "Exits the program.",
            "exit");

    // All advanced commands below

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

    public static ObservableList<CommandFormat> getBasicCommands() {
        return FXCollections.observableArrayList(
                ADD_COMMAND_FORMAT,
                LIST_COMMAND_FORMAT,
                VIEW_COMMAND_FORMAT,
                DELETE_COMMAND_FORMAT,
                EXIT_COMMAND_FORMAT
                );
    }

    // Currently no advanced commands implemented
    // **As of v1.2**
    public static ObservableList<CommandFormat> getAdvancedCommands() {
        return FXCollections.observableArrayList();
    }

    public String getCommand() {
        return this.command.get();
    }

    public String getUsage() {
        return this.usage.get();
    }
}

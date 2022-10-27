package seedu.clinkedin.ui.utils;

/**
 * Represents a table row containing the information about a command
 * in the command list.
 */
public class CommandRow {
    private String commandName;
    private String commandUsage;

    /**
     * Creates a new CommandRow.
     * @param commandName Name of the command.
     * @param commandUsage Usage of the command.
     */
    public CommandRow(String commandName, String commandUsage) {
        this.commandName = commandName;
        this.commandUsage = commandUsage;
    }

    /**
     * Returns the name of the command.
     * @return Name of the command.
     */
    public String getCommandName() {
        return commandName;
    }

    /**
     * Returns the usage of the command.
     * @return Usage of the command.
     */
    public String getCommandUsage() {
        return commandUsage;
    }
}

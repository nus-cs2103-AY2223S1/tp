package seedu.rc4hdb.logic.commands.modelcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.model.Model;

/**
 * Updates the table view to show only the columns specified by the user.
 */
public class ShowCommand implements ModelCommand {

    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows only the columns specified "
            + "(case-insensitive) after the command word\n"
            + "Parameters: FIELD [MORE_FIELDS]...\n"
            + "Example: " + COMMAND_WORD + " name phone email";

    public static final String MESSAGE_SUCCESS = "Showing only the specified columns. "
            + "Use the list command to restore all columns.";

    /**
     * The list of fields to pass to the TableView for hiding.
     */
    public final List<String> fieldsToHide;

    /**
     * Constructor for a ShowCommand instance.
     * @param fieldsToHide The list of fields to hide in the table
     */
    public ShowCommand(List<String> fieldsToHide) {
        requireNonNull(fieldsToHide);
        this.fieldsToHide = fieldsToHide;
    }

    /**
     * Implements the execute method in the ModelCommand interface.
     * The model field list is updated with the internal field list.
     * @param model {@code Model} which the command should operate on.
     * @return A CommandResult if the execution was successful.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setObservableFields(fieldsToHide);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Overrides the equals method in the Object class.
     * Checks if the field lists have the same content.
     * @param other The object to check for equality
     * @return True if the object is equals to this instance
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof ShowCommand) {
            ShowCommand otherCommand = (ShowCommand) other;
            return this.fieldsToHide.containsAll(otherCommand.fieldsToHide)
                    && otherCommand.fieldsToHide.containsAll(this.fieldsToHide);
        }
        return false;
    }
}

package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    protected static final String MESSAGE_RECORD_COMMAND_PREREQUISITE =
            "A person's record list should be displayed before calling record commands\n"
            + "(Hint: rlist)";
    protected static final String MESSAGE_ADDRESS_BOOK_COMMAND_PREREQUISITE =
            "The patient list should be displayed before calling patient commands\n"
            + "(Hint: list)";

    protected static final String MESSAGE_LIST_COMMAND_PREREQUISITE =
            "List should be used to navigate back to the patient list panel from the record list panel.\n"
                    + "A person's record list should be displayed first before calling the list command.\n"
                    + "(Hint: Use showall to view the unfiltered list )";
    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

}

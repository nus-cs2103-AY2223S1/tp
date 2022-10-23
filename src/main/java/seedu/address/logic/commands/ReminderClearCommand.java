package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 *  Clears all reminders in the reminder list.
 */
public class ReminderClearCommand extends ReminderCommandGroup {
    public static final String COMMAND_SPECIFIER = "clear";
    public static final String COMMAND_SPECIFIER_ALIAS = "c";
    public static final String COMMAND_WORD = COMMAND_GROUP + " " + COMMAND_SPECIFIER;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears the currently displayed reminders";

    public static final String MESSAGE_SUCCESS = "Reminders cleared.";
    public static final String MESSAGE_SUCCESS_TARGET_PERSON_LIST = "Reminders cleared for %1$s.";

    public ReminderClearCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.clearCurrentReminderList();
        String successMessage = model.hasTargetPerson()
                ? String.format(MESSAGE_SUCCESS_TARGET_PERSON_LIST, model.getTargetPerson().getName().fullName)
                : MESSAGE_SUCCESS;

        return new CommandResult(successMessage);
    }
}

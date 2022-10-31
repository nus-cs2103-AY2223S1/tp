package seedu.address.logic.commands.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_SORTING_CRITERIA;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Sort reminders by their deadline, from earliest to latest chronically.
 * Reminders with same deadline will be sorted lexicographically by their names.
 */
public class SortReminderCommand extends Command {
    public static final String COMMAND_WORD = "sort reminder";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort reminders by either by a chosen criteria";

    public static final String MESSAGE_SUCCESS_TEMPLATE = "Reminders sorted by %s";

    public static final String CRITERIA_PRIORITY = "priority";
    public static final String CRITERIA_DEADLINE = "deadline";
    private final String criteria;


    public SortReminderCommand(String criteria) {
        this.criteria = criteria;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        switch (criteria) {
        case CRITERIA_PRIORITY:
            model.sortReminderByPriority();
            break;
        case CRITERIA_DEADLINE:
            model.sortReminderByDeadline();
            break;
        default:
            throw new CommandException(MESSAGE_INVALID_SORTING_CRITERIA);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS_TEMPLATE, criteria));
    }
}

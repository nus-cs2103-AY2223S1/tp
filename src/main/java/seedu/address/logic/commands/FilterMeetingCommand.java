package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.MeetingFilterDatePredicate;

/**
 * Finds and lists all meetings in the application whose description contains the argument keyword.
 * Keyword matching is case insensitive.
 */
public class FilterMeetingCommand extends Command {

    public static final String COMMAND_WORD = "filtermeetingsbetween";
    public static final String INVALID_DATE_FORMAT = "Invalid Date Time Format/Invalid Date Time. \n"
            + "Please follow the (dd-MM-yyyy HHmm) format and ensure that the date entered is a valid one.";
    public static final String INVALID_DATE_POSITION = "First date must be before the second date!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all meetings between the given dates "
            + "displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD DATE1 ;;; DATE2\n"
            + "Example: " + COMMAND_WORD + " 10-10-2022 0000 ;;; 01-11-2022 0000";

    private final MeetingFilterDatePredicate predicate;

    public FilterMeetingCommand(MeetingFilterDatePredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the command and returns the result message.
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredMeetingList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW,
                model.getFilteredMeetingList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterMeetingCommand // instanceof handles nulls
                && predicate.equals(((FilterMeetingCommand) other).predicate)); // state check
    }
}

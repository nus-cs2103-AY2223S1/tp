package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingContainsKeywordsPredicate;

/**
 * Finds and lists all meetings in the application whose description contains the argument keyword.
 * Keyword matching is case insensitive.
 */
public class FindMeetingCommand extends Command {

    public static final String COMMAND_WORD = "findmeeting";
    public static final String FIND_AT = "/at";
    public static final String FIND_DESCRIPTION = "/named";
    public static final String FIND_WITH = "/with";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all meetings matching the description/location/people "
            + "the specified keywords (not case sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: /at [KEYWORDS] or /named [KEYWORDS] or /with [KEYWORDS] \n"
            + "Example 1: " + COMMAND_WORD + " /at utown\n"
            + "Example 2: " + COMMAND_WORD + " /named CS2103\n"
            + "Example 3: " + COMMAND_WORD + " /with John Doe\n";


    private final MeetingContainsKeywordsPredicate predicate;

    public FindMeetingCommand(MeetingContainsKeywordsPredicate predicate) {
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

        StringBuilder str = new StringBuilder();
        List<Meeting> list = model.getFilteredMeetingList().stream().collect(Collectors.toList());
        list.forEach(str::append);

        return new CommandResult(String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW,
                model.getFilteredMeetingList().size()) + "\n" + str);
    }
}

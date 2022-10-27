package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;

import java.util.Comparator;

import seedu.address.commons.SortInfo;
import seedu.address.logic.commands.SortTaskPriorityCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.SortByPriority;
import seedu.address.model.task.Task;

/**
 * Parses input arguments and creates a new SortTaskDeadlineCommand object
 */
public class SortTaskPriorityParser implements Parser<SortTaskPriorityCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortTaskPriorityCommand
     * and return a SortTaskPriorityCommand object for execution.
     *
     * @param args String of arguments to be parsed
     * @return a SortTaskPriorityCommand object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortTaskPriorityCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ORDER);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortTaskPriorityCommand.MESSAGE_USAGE));
        }

        Comparator<Task> comparator = new SortByPriority().reversed();
        StringBuilder sortInfo = new StringBuilder("priority ");

        if (argMultimap.getValue(PREFIX_ORDER).isPresent()) {
            switch (argMultimap.getValue(PREFIX_ORDER).get().toLowerCase()) {
            case "d":
            case "desc":
                sortInfo.append("(descending)");
                break;
            case "a":
            case "asc":
                comparator = comparator.reversed();
                sortInfo.append("(ascending)");
                break;
            default:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortTaskPriorityCommand.MESSAGE_USAGE));
            }
        } else {
            sortInfo.append("(descending)");
        }

        return new SortTaskPriorityCommand(comparator, new SortInfo(sortInfo.toString()));
    }

}

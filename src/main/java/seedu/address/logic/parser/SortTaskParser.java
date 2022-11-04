package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.Comparator;

import seedu.address.commons.SortInfo;
import seedu.address.logic.commands.SortTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.SortByDeadline;
import seedu.address.model.task.SortByPriority;
import seedu.address.model.task.Task;

/**
 * Parses input arguments and creates a new SortTaskCommand object
 */
public class SortTaskParser implements Parser<SortTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortTaskCommand
     * and returns a SortTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public SortTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PRIORITY, PREFIX_DEADLINE);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortTaskCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent() && argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            throw new ParseException("Only one parameter should be provided.");
        }

        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            Comparator<Task> comparator = new SortByPriority();
            StringBuilder sortInfo = new StringBuilder("priority ");

            switch (argMultimap.getValue(PREFIX_PRIORITY).get().toLowerCase()) {
            case "asc":
                sortInfo.append("(ascending)");
                break;
            case "desc":
                sortInfo.append("(descending)");
                comparator = comparator.reversed();
                break;

            default:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortTaskCommand.MESSAGE_USAGE));
            }
            return new SortTaskCommand(comparator, new SortInfo(sortInfo.toString()));

        } else if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            Comparator<Task> comparator = new SortByDeadline();
            StringBuilder sortInfo = new StringBuilder("deadline ");

            switch (argMultimap.getValue(PREFIX_DEADLINE).get().toLowerCase()) {
            case "asc":
                sortInfo.append("(ascending)");
                break;
            case "desc":
                comparator = comparator.reversed();
                sortInfo.append("(descending)");
                break;
            default:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortTaskCommand.MESSAGE_USAGE));
            }
            return new SortTaskCommand(comparator, new SortInfo(sortInfo.toString()), "deadline");

        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortTaskCommand.MESSAGE_USAGE));
        }
    }
}

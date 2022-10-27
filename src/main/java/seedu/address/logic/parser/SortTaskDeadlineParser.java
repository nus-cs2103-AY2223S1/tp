package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;

import java.util.Comparator;

import seedu.address.logic.commands.SortTaskDeadlineCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.SortByDeadline;
import seedu.address.model.task.Task;

/**
 * Parses input arguments and creates a new SortTaskDeadlineCommand object
 */
public class SortTaskDeadlineParser implements Parser<SortTaskDeadlineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortTaskDeadlineCommand
     * and return a SortTaskDeadlineCommand object for execution.
     *
     * @param args String of arguments to be parsed
     * @return a SortTaskDeadlineCommand object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortTaskDeadlineCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ORDER);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortTaskDeadlineCommand.MESSAGE_USAGE));
        }

        Comparator<Task> comparator = new SortByDeadline();

        if (argMultimap.getValue(PREFIX_ORDER).isPresent()) {
            switch (argMultimap.getValue(PREFIX_ORDER).get().toLowerCase()) {
            case "a":
            case "asc":
                break;
            case "d":
            case "desc":
                comparator = comparator.reversed();
                break;
            default:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortTaskDeadlineCommand.MESSAGE_USAGE));
            }
        }

        return new SortTaskDeadlineCommand(comparator);
    }

}

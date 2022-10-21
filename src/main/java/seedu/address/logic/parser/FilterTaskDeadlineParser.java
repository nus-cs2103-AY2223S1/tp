package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;

import java.util.stream.Stream;

import seedu.address.logic.commands.FilterTaskDeadlineCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskDeadlineContainsDatePredicate;

/**
 * Parses input arguments and creates a new FilterTaskDeadlineCommand object
 */
public class FilterTaskDeadlineParser implements Parser<FilterTaskDeadlineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterTaskDeadlineCommand
     * and return a FilterTaskDeadline object for execution.
     * @param args String of arguments to be parsed
     * @return a FilterTaskDeadline object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterTaskDeadlineCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DEADLINE);

        if (!arePrefixesPresent(argMultimap, PREFIX_DEADLINE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterTaskDeadlineCommand.MESSAGE_USAGE));
        }

        TaskDeadline date = ParserUtil.parseTaskDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());

        return new FilterTaskDeadlineCommand(new TaskDeadlineContainsDatePredicate(date));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

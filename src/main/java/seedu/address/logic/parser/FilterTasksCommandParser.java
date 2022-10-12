package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.stream.Stream;

import seedu.address.logic.commands.FilterTasksCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;
import seedu.address.model.task.FilterPredicate;
import seedu.address.model.task.TaskBelongsToModulePredicate;
import seedu.address.model.task.TaskStatus;

/**
 * Parses input arguments and creates a new FilterTasksCommand object
 */
public class FilterTasksCommandParser implements Parser<FilterTasksCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FilterTasksCommand
     * and returns a FilterTasksCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterTasksCommand parse(String args) throws ParseException {
        Module module = null;
        TaskStatus status = null;

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_STATUS);

        if (!isPrefixPresent(argMultimap, PREFIX_MODULE, PREFIX_STATUS) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterTasksCommand.MESSAGE_USAGE));
        }

        if (hasPrefix(argMultimap, PREFIX_MODULE)) {
            module = ParserUtil.parseModule(argMultimap.getValue(PREFIX_MODULE).get());
        }
//        module = ParserUtil.parseModule(argMultimap.getValue(PREFIX_MODULE).get());

        if (hasPrefix(argMultimap, PREFIX_STATUS)) {
            status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
        }
//        status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());

        return new FilterTasksCommand(new FilterPredicate(module, status));
    }

    /**
     * Returns true if at least one prefix is not empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static boolean hasPrefix(ArgumentMultimap argumentMultimap, Prefix prefix) {
        return argumentMultimap.getValue(prefix).isPresent();
    }
}

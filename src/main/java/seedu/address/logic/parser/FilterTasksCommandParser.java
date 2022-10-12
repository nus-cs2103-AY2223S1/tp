package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import java.util.stream.Stream;

import seedu.address.logic.commands.FilterTasksCommand;
import seedu.address.logic.commands.FindTasksCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.task.TaskBelongsToModulePredicate;

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
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterTasksCommand.MESSAGE_USAGE));
        }

        Module module = ParserUtil.parseModule(argMultimap.getValue(PREFIX_MODULE).get());

        return new FilterTasksCommand(new TaskBelongsToModulePredicate(module));
    }

    /**
     * Returns true if at least one prefix is not empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

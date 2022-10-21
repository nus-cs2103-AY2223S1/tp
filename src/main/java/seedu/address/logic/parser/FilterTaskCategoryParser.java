package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;

import java.util.stream.Stream;

import seedu.address.logic.commands.FilterTaskCategoryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.TaskCategory;
import seedu.address.model.task.TaskContainsCategoryPredicate;

/**
 * Parses input arguments and creates a new FilterTaskCategory object
 */
public class FilterTaskCategoryParser implements Parser<FilterTaskCategoryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterTaskCategory
     * and return a FilterTaskCategoryCommand object for execution.
     * @param args String of arguments to be parsed
     * @return a FilterTaskCategoryCommand object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterTaskCategoryCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY);

        if (!arePrefixesPresent(argMultimap, PREFIX_CATEGORY) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterTaskCategoryCommand.MESSAGE_USAGE));
        }

        TaskCategory date = ParserUtil.parseTaskCategory(argMultimap.getValue(PREFIX_CATEGORY).get());

        return new FilterTaskCategoryCommand(new TaskContainsCategoryPredicate(date));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

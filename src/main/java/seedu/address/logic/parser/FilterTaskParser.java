package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import seedu.address.logic.commands.FilterTaskCommand;
import seedu.address.logic.commands.FilterTaskCommand.FilterTaskDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FilterTaskCommand object
 */
public class FilterTaskParser implements Parser<FilterTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterTaskCommand
     * and returns a FilterTaskCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public FilterTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY, PREFIX_DATE);

        FilterTaskDescriptor filterTaskDescriptor = new FilterTaskDescriptor();
        if (argMultimap.getValue(PREFIX_CATEGORY).isPresent()) {
            filterTaskDescriptor.setCategory(ParserUtil.parseTaskCategory(argMultimap.getValue(PREFIX_CATEGORY).get()));
        }

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            filterTaskDescriptor.setDate(ParserUtil.parseTaskDate(argMultimap.getValue(PREFIX_DATE).get()));
        }

        if (!filterTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterTaskCommand.MESSAGE_USAGE));
        }

        return new FilterTaskCommand(filterTaskDescriptor);
    }
}

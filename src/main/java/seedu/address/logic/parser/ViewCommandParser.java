package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRAPH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * object and returns a ViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TYPE, PREFIX_MONTH, PREFIX_GRAPH);

        ViewCommand.ViewEntriesDescriptor viewEntriesDescriptor = new ViewCommand.ViewEntriesDescriptor();
        try {
            if (argMultimap.getValue(PREFIX_TYPE).isPresent()) {
                viewEntriesDescriptor.setEntryType(
                        ParserUtil.parseEntryType(argMultimap.getValue(PREFIX_TYPE).get()));
            }
            if (argMultimap.getValue(PREFIX_GRAPH).isPresent()) {
                viewEntriesDescriptor.setGraphType(
                        ParserUtil.parseGraphType(argMultimap.getValue(PREFIX_GRAPH).get()));
            }
            if (argMultimap.getValue(PREFIX_MONTH).isPresent()) {
                viewEntriesDescriptor.setMonth(
                        ParserUtil.parseMonth(argMultimap.getValue(PREFIX_MONTH).get()));
            }
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE), pe);
        }

        if (!viewEntriesDescriptor.isValid()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        return new ViewCommand(viewEntriesDescriptor);
    }

}


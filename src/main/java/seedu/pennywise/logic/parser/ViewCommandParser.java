package seedu.pennywise.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.pennywise.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pennywise.logic.parser.CliSyntax.PREFIX_MONTH;
import static seedu.pennywise.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.pennywise.logic.commands.ViewCommand;
import seedu.pennywise.logic.commands.ViewCommand.ViewEntriesDescriptor;
import seedu.pennywise.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code ViewCommand} object.
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code ViewCommand}
     * object and returns a {@code ViewCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TYPE, PREFIX_MONTH);

        ViewEntriesDescriptor viewEntriesDescriptor = new ViewEntriesDescriptor();
        try {
            if (argMultimap.getValue(PREFIX_TYPE).isPresent()) {
                viewEntriesDescriptor.setEntryType(
                        ParserUtil.parseEntryType(argMultimap.getValue(PREFIX_TYPE).get()));
            } else {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
            }
            if (argMultimap.getValue(PREFIX_MONTH).isPresent()) {
                viewEntriesDescriptor.setYearMonth(
                        ParserUtil.parseYearMonth(argMultimap.getValue(PREFIX_MONTH).get()));
            }

        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE), pe);
        }

        return new ViewCommand(viewEntriesDescriptor);
    }

}


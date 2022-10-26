package paymelah.logic.parser;

import static paymelah.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static paymelah.logic.parser.CliSyntax.PREFIX_DEBT;
import static paymelah.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.Set;

import paymelah.commons.core.index.Index;
import paymelah.logic.commands.UnmarkCommand;
import paymelah.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnmarkCommand object
 */
public class UnmarkCommandParser implements Parser<UnmarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnmarkCommand
     * and returns a UnmarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnmarkCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DEBT);

        if (argMultimap.getPreamble().isEmpty()
                || !arePrefixesPresent(argMultimap, PREFIX_DEBT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE));
        }

        try {
            Index personIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            Set<Index> debtIndexSet = ParserUtil.parseIndices(argMultimap.getValue(PREFIX_DEBT).get());
            return new UnmarkCommand(personIndex, debtIndexSet);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE), pe);
        }
    }


}

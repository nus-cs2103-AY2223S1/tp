package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnlinkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnlinkCommand object
 */
public class UnlinkCommandParser implements Parser<UnlinkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnlinkCommand
     * and returns an UnlinkCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnlinkCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args,
                        PREFIX_PERSON,
                        PREFIX_INTERNSHIP);

        if (!(arePrefixesPresent(argMultimap, PREFIX_PERSON) || arePrefixesPresent(argMultimap, PREFIX_INTERNSHIP))
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnlinkCommand.MESSAGE_USAGE));
        }

        Index personIndex = null;
        Index internshipIndex = null;

        if (argMultimap.getValue(PREFIX_PERSON).isPresent()) {
            personIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PERSON).get());
        }
        if (argMultimap.getValue(PREFIX_INTERNSHIP).isPresent()) {
            internshipIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INTERNSHIP).get());
        }

        return new UnlinkCommand(personIndex, internshipIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

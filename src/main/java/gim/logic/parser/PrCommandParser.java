package gim.logic.parser;

import static gim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static gim.logic.parser.CliSyntax.PREFIX_ALL;
import static gim.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import gim.logic.commands.PrCommand;
import gim.logic.parser.exceptions.ParseException;
import gim.model.exercise.Name;

/**
 * Parses input arguments and creates a new PRCommand object
 */
public class PrCommandParser implements Parser<PrCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PRCommand
     * and returns an PRCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PrCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ALL);
        if (arePrefixesPresent(argMultimap, PREFIX_ALL)) {
            return new PrCommand(new HashSet<Name>());
        }
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PrCommand.MESSAGE_USAGE));
        }
        Set<Name> nameSet = ParserUtil.parseNames(argMultimap.getAllValues(PREFIX_NAME));
        return new PrCommand(nameSet);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

package foodwhere.logic.parser;

import static foodwhere.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Set;
import java.util.stream.Stream;

import foodwhere.logic.commands.SFindCommand;
import foodwhere.logic.parser.exceptions.ParseException;
import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;
import foodwhere.model.stall.StallContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new SFindCommand object
 */
public class SFindCommandParser implements Parser<SFindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SFindCommand
     * and returns a SFindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SFindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        CliSyntax.PREFIX_NAME,
                        CliSyntax.PREFIX_TAG);

        if ((!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_NAME)
                && !arePrefixesPresent(argMultimap, CliSyntax.PREFIX_TAG))
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SFindCommand.MESSAGE_USAGE));
        }

        Set<Name> nameList = ParserUtil.parseNameList(argMultimap.getAllValues(CliSyntax.PREFIX_NAME));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(CliSyntax.PREFIX_TAG));

        return new SFindCommand(new StallContainsKeywordsPredicate(nameList, tagList));
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

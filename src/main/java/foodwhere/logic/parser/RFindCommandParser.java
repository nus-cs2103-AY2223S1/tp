package foodwhere.logic.parser;

import static foodwhere.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import foodwhere.logic.commands.RFindCommand;
import foodwhere.logic.parser.exceptions.ParseException;
import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;
import foodwhere.model.review.ReviewContainsKeywordsPredicate;


/**
 * Parses input arguments and creates a new RFindCommand object
 */
public class RFindCommandParser implements Parser<RFindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RFindCommand
     * and returns a RFindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public RFindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args,
                    CliSyntax.PREFIX_NAME,
                    CliSyntax.PREFIX_TAG);

        if ((!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_NAME)
                && !arePrefixesPresent(argMultimap, CliSyntax.PREFIX_TAG))
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RFindCommand.MESSAGE_USAGE));
        }

        Set<Name> nameSet = new HashSet<>();
        Set<Tag> tagSet = new HashSet<>();
        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_NAME)) {
            nameSet = ParserUtil.parseNameList(argMultimap.getValue(CliSyntax.PREFIX_NAME).get());
        }
        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_TAG)) {
            tagSet = ParserUtil.parseTagList(argMultimap.getValue(CliSyntax.PREFIX_TAG).get());
        }

        return new RFindCommand(new ReviewContainsKeywordsPredicate(nameSet, tagSet));
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

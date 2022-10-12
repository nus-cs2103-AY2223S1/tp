package seedu.foodrem.logic.parser;


import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.logic.commands.tagcommands.UntagCommand;
import seedu.foodrem.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_TAG_NAME;

/**
 * Parses input arguments and creates a new UntagCommand object
 */
public class UntagCommandParser implements Parser<UntagCommand>{

    /**
     * Parses the given {@code String} of arguments in the context of the UntagCommand
     * and returns an UntagCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UntagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG_NAME, PREFIX_ID);
        if (!arePrefixesPresent(argMultimap, PREFIX_TAG_NAME, PREFIX_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UntagCommand.MESSAGE_USAGE));
        }
        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_ID).get());
        String name = ParserUtil.parseTagName(argMultimap.getValue(PREFIX_TAG_NAME).get()).toString();

        return new UntagCommand(name, index);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

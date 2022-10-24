package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TagEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new TagEventCommand object
 */
public class TagEventCommandParser implements Parser<TagEventCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TagEventCommand
     * and returns an TagEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TagEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PERSONS);

        Index eventIndex;
        try {
            eventIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    seedu.address.logic.commands.TagEventCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_PERSONS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    seedu.address.logic.commands.TagEventCommand.MESSAGE_USAGE));
        }
        List<Index> personIndexes = ParserUtil.parseIndexes(argMultimap.getValue(PREFIX_PERSONS).get());
        return new seedu.address.logic.commands.TagEventCommand(eventIndex, personIndexes);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return java.util.stream.Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}


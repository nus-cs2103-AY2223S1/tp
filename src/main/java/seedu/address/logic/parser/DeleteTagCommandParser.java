package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses the user input for the command to create a DeleteTagCommand object.
 */
public class DeleteTagCommandParser implements Parser<DeleteTagCommand> {

    public static final String INVALID_INDEX_FOR_DELETE_TAG = "The index for tagdel should"
            + "be greater than 0 and less than 2147483648.";
    @Override
    public DeleteTagCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(userInput, PREFIX_TAG);
        Index index;
        if (!isTagPrefixPresent(argMultiMap)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteTagCommand.MESSAGE_USAGE));
        }
        try {
            index = ParserUtil.parseIndex(argMultiMap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(INVALID_INDEX_FOR_DELETE_TAG);
        }
        Set<String> tags = ParserUtil.parseDeleteTagKeywords(argMultiMap.getValue(PREFIX_TAG)
                .orElse(null));
        return new DeleteTagCommand(index, tags);
    }

    private static boolean isTagPrefixPresent(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_TAG).isPresent();
    }
}

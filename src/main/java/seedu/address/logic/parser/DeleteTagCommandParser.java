package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses the user input for the command to create a DeleteTagCommand object.
 */
public class DeleteTagCommandParser implements Parser<DeleteTagCommand> {
    public static final String INVALID_INDEX_FOR_DELETE_TAG = "The index for tagdel should"
            + " be an unsigned positive integer greater than 0 and lesser than 2147483648.";
    private final Logger logger = LogsCenter.getLogger(DeleteTagCommand.class);
    @Override
    public DeleteTagCommand parse(String userInput) throws ParseException {
        logger.info("Parsing arguments for DeleteTagCommand.");
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(userInput, PREFIX_TAG);
        Index index;
        if (!isTagPrefixPresent(argMultiMap)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteTagCommand.MESSAGE_USAGE));
        }

        if (!argMultiMap.getPreamble().matches("(-|\\+)?\\d+(\\.\\d+)?")) {
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

    //@@author dlimyy-reused
    //Reused with minor modifications from existing AB3 (https://github.com/nus-cs2103-AY2223S1/tp)
    private static boolean isTagPrefixPresent(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_TAG).isPresent();
    }
    //@@author
}

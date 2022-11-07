package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.DeleteTagCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteTagCommand object.
 */
public class DeleteTagCommandParser implements Parser<DeleteTagCommand> {
    /**
     * Parses the given arguments in the context of the DeleteTagCommand
     * and returns a DeleteTagCommand object for execution.
     *
     * @param args The string of arguments given.
     * @return DeleteTagCommand
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTagCommand parse(String args) throws ParseException {
        requireAllNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        List<Index> indices = ParserUtil.parseTwoIndex(argMultimap.getPreamble());

        return new DeleteTagCommand(indices.get(0), indices.get(1));
    }
}

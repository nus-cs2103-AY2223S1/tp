package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.DeleteRemarkCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteRemarkCommand object.
 */
public class DeleteRemarkCommandParser implements Parser<DeleteRemarkCommand> {
    /**
     * Parses the given arguments in the context of the DeleteRemarkCommand
     * and returns a DeleteRemarkCommand object for execution.
     *
     * @param args the string of arguments given
     * @return DeleteRemarkCommand
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteRemarkCommand parse(String args) throws ParseException {
        requireAllNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        List<Index> indices = ParserUtil.parseTwoIndex(argMultimap.getPreamble());

        return new DeleteRemarkCommand(indices.get(0), indices.get(1));
    }
}

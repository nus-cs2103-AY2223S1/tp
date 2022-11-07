package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.DeleteConditionCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteConditionCommand object.
 */
public class DeleteConditionCommandParser implements Parser<DeleteConditionCommand> {
    /**
     * Parses the given arguments in the context of the DeleteConditionCommand
     * and returns a DeleteConditionCommand object for execution.
     *
     * @param args The string of arguments given.
     * @return DeleteConditionCommand
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteConditionCommand parse(String args) throws ParseException {
        requireAllNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        List<Index> indices = ParserUtil.parseTwoIndex(argMultimap.getPreamble());

        return new DeleteConditionCommand(indices.get(0), indices.get(1));
    }
}

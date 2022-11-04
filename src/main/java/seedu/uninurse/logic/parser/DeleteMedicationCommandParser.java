package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.DeleteMedicationCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteMedicationCommand object.
 */
public class DeleteMedicationCommandParser implements Parser<DeleteMedicationCommand> {
    /**
     * Parses the given arguments in the context of the DeleteMedicationCommand
     * and returns a DeleteMedicationCommand object for execution.
     *
     * @param args the string of arguments given
     * @return DeleteMedicationCommand
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteMedicationCommand parse(String args) throws ParseException {
        requireAllNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        List<Index> indices = ParserUtil.parseTwoIndex(argMultimap.getPreamble());

        return new DeleteMedicationCommand(indices.get(0), indices.get(1));
    }
}

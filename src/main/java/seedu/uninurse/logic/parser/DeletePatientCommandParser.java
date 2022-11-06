package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.DeletePatientCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeletePatientCommand object
 */
public class DeletePatientCommandParser implements Parser<DeletePatientCommand> {

    /**
     * Parses the given arguments in the context of the DeletePatientCommand
     * and returns a DeletePatientCommand object for execution.
     *
     * @param args The given string of arguments.
     * @return DeletePatientCommand.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public DeletePatientCommand parse(String args) throws ParseException {
        requireAllNonNull(args);
        Index index = ParserUtil.parseIndex(args);

        return new DeletePatientCommand(index);
    }
}

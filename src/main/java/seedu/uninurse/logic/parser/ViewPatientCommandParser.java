package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIXES_OPTION_ALL;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.ViewPatientCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewPatientCommand object.
 */
public class ViewPatientCommandParser implements Parser<ViewPatientCommand> {
    /**
     * Parses the given arguments in the context of the ViewPatientCommand
     * and returns a ViewPatientCommand object for execution.
     * 
     * @param args the string of arguments given
     * @return ViewPatientCommand
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewPatientCommand parse(String args) throws ParseException {
        requireAllNonNull(args);

        ArgumentMultimap options = ParserUtil.parseOptions(args, PREFIXES_OPTION_ALL);

        Index index = ParserUtil.parseIndex(options.getValue(PREFIX_OPTION_PATIENT_INDEX).orElse(""));

        return new ViewPatientCommand(index);
    }
}

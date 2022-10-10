package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates either a new EditPatientCommand object or
 * a new EditTaskCommand object based on option values.
 */
public class AddGenericCommandParser implements Parser<Command> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditPatientCommand
     * and returns an EditPatientCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap options = ParserUtil.parseOptions(args, PREFIX_OPTION_PATIENT_INDEX);
        args = ParserUtil.eraseOptions(args, PREFIX_OPTION_PATIENT_INDEX);

        if (!options.getValue(PREFIX_OPTION_PATIENT_INDEX).isPresent()) {
            return new AddCommandParser().parse(args);
        }

        if (options.getValue(PREFIX_OPTION_PATIENT_INDEX).isPresent()) {
            String patientIndex = options.getValue(PREFIX_OPTION_PATIENT_INDEX).get();
            return new AddTaskCommandParser().parse(patientIndex + " " + args);
        }

        throw new ParseException(ParserUtil.MESSAGE_INVALID_OPTIONS);
    }
}

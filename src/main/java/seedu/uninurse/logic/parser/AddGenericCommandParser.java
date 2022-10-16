package seedu.uninurse.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;

import java.util.Optional;

import seedu.uninurse.logic.commands.AddGenericCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates either a new AddPatientCommand object or
 * a new AddTaskCommand object based on option values.
 */
public class AddGenericCommandParser implements Parser<AddGenericCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddGenericCommand
     * and returns an AddGenericCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddGenericCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap options = ParserUtil.parseOptions(args, PREFIX_OPTION_PATIENT_INDEX);
        args = ParserUtil.eraseOptions(args, PREFIX_OPTION_PATIENT_INDEX);

        Optional<String> patientIndex = options.getValue(PREFIX_OPTION_PATIENT_INDEX);

        if (!patientIndex.isPresent()) {
            return new AddPatientCommandParser().parse(args);
        }

        if (patientIndex.isPresent()) {
            return new AddTaskCommandParser().parse(patientIndex.get() + " " + args);
        }

        throw new ParseException(ParserUtil.MESSAGE_INVALID_OPTIONS);
    }
}

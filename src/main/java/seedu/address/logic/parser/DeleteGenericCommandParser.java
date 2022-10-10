package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION_TASK_INDEX;

import seedu.address.logic.commands.DeleteGenericCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates either a new DeletePatientCommand object or
 * a new DeleteTaskCommand object based on option values.
 */
public class DeleteGenericCommandParser implements Parser<DeleteGenericCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteGenericCommand
     * and returns an DeleteGenericCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteGenericCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap options = ParserUtil.parseOptions(args, PREFIX_OPTION_PATIENT_INDEX, PREFIX_OPTION_TASK_INDEX);
        args = ParserUtil.eraseOptions(args, PREFIX_OPTION_PATIENT_INDEX, PREFIX_OPTION_TASK_INDEX);

        if (options.getValue(PREFIX_OPTION_PATIENT_INDEX).isPresent()
                && !options.getValue(PREFIX_OPTION_TASK_INDEX).isPresent()) {
            String patientIndex = options.getValue(PREFIX_OPTION_PATIENT_INDEX).get();
            return new DeleteCommandParser().parse(patientIndex + " " + args);
        }

        if (options.getValue(PREFIX_OPTION_PATIENT_INDEX).isPresent()
                && options.getValue(PREFIX_OPTION_TASK_INDEX).isPresent()) {
            String patientIndex = options.getValue(PREFIX_OPTION_PATIENT_INDEX).get();
            String taskIndex = options.getValue(PREFIX_OPTION_TASK_INDEX).get();
            return new DeleteTaskCommandParser().parse(patientIndex + " " + taskIndex + " " + args);
        }

        throw new ParseException(ParserUtil.MESSAGE_INVALID_OPTIONS);
    }
}

package seedu.uninurse.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_TASK_INDEX;

import java.util.Optional;

import seedu.uninurse.logic.commands.EditGenericCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates either a new EditPatientCommand object or
 * a new EditTaskCommand object based on option values.
 */
public class EditGenericCommandParser implements Parser<EditGenericCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditGenericCommand
     * and returns an EditGenericCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditGenericCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap options = ParserUtil.parseOptions(args, PREFIX_OPTION_PATIENT_INDEX, PREFIX_OPTION_TASK_INDEX);
        args = ParserUtil.eraseOptions(args, PREFIX_OPTION_PATIENT_INDEX, PREFIX_OPTION_TASK_INDEX);

        Optional<String> patientIndex = options.getValue(PREFIX_OPTION_PATIENT_INDEX);
        Optional<String> taskIndex = options.getValue(PREFIX_OPTION_TASK_INDEX);

        if (patientIndex.isPresent() && !taskIndex.isPresent()) {
            return new EditPatientCommandParser().parse(patientIndex.get() + " " + args);
        }

        if (patientIndex.isPresent() && taskIndex.isPresent()) {
            return new EditTaskCommandParser().parse(patientIndex.get() + " " + taskIndex.get() + " " + args);
        }

        throw new ParseException(ParserUtil.MESSAGE_INVALID_OPTIONS);
    }
}

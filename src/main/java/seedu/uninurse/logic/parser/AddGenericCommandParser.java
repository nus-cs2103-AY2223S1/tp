package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIXES_OPTION_ALL;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIXES_PATIENT_ALL;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import seedu.uninurse.logic.commands.AddGenericCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates either a new AddGenericCommand
 * object based on option values.
 */
public class AddGenericCommandParser implements Parser<AddGenericCommand> {
    /**
     * Parses the given String of arguments in the context of the AddGenericCommand
     * and returns an AddGenericCommand object for execution.
     *
     * @param args The given user input to be parsed.
     * @return AddGenericCommand.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AddGenericCommand parse(String args) throws ParseException {
        requireAllNonNull(args);

        ArgumentMultimap options = ParserUtil.parseOptions(args, PREFIXES_OPTION_ALL);
        args = ParserUtil.eraseOptions(args, PREFIXES_OPTION_ALL);

        ArgumentMultimap parameters = ArgumentTokenizer.tokenize(args, PREFIXES_PATIENT_ALL);

        if (ParserUtil.optionsExactlyContains(options)) {
            return new AddPatientCommandParser().parse(args);
        }

        // args contain an option other than PATIENT_INDEX
        if (!ParserUtil.optionsExactlyContains(options, PREFIX_OPTION_PATIENT_INDEX)) {
            throw new ParseException(ParserUtil.MESSAGE_INVALID_OPTIONS);
        }

        String patientIndex = options.getValue(PREFIX_OPTION_PATIENT_INDEX).get();

        if (ParserUtil.parametersExactlyContains(parameters, PREFIX_TASK_DESCRIPTION)) {
            return new AddTaskCommandParser().parse(patientIndex + " " + args);
        }

        if (ParserUtil.parametersExactlyContains(parameters, PREFIX_TAG)) {
            return new AddTagCommandParser().parse(patientIndex + " " + args);
        }

        if (ParserUtil.parametersExactlyContains(parameters, PREFIX_CONDITION)) {
            return new AddConditionCommandParser().parse(patientIndex + " " + args);
        }

        if (ParserUtil.parametersExactlyContains(parameters, PREFIX_MEDICATION)) {
            return new AddMedicationCommandParser().parse(patientIndex + " " + args);
        }

        if (ParserUtil.parametersExactlyContains(parameters, PREFIX_REMARK)) {
            return new AddRemarkCommandParser().parse(patientIndex + " " + args);
        }

        throw new ParseException(ParserUtil.MESSAGE_INVALID_PARAMETERS);
    }
}

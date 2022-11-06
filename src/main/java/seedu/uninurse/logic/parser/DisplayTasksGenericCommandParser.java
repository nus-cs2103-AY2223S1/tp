package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIXES_OPTION_ALL;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;
import static seedu.uninurse.logic.parser.CliSyntax.SPECIAL_CHARACTER_ALL;
import static seedu.uninurse.logic.parser.CliSyntax.SPECIAL_CHARACTER_TODAY;

import seedu.uninurse.logic.commands.DisplayTasksGenericCommand;
import seedu.uninurse.logic.commands.ListTaskCommand;
import seedu.uninurse.logic.commands.PatientsTodayCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates either a new DisplayTasksGenericCommand
 * object based on option values.
 */
public class DisplayTasksGenericCommandParser implements Parser<DisplayTasksGenericCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DisplayTasksGenericCommand
     * and returns an DisplayTasksGenericCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DisplayTasksGenericCommand parse(String args) throws ParseException {
        requireAllNonNull(args);

        ArgumentMultimap options = ParserUtil.parseOptions(args, PREFIXES_OPTION_ALL);
        args = ParserUtil.eraseOptions(args, PREFIXES_OPTION_ALL);

        ArgumentMultimap parameters = ArgumentTokenizer.tokenize(args);

        if (ParserUtil.optionsOnlyContains(options)
                && parameters.getPreamble().trim().equals(SPECIAL_CHARACTER_TODAY)) {
            return new PatientsTodayCommand();
        }

        if (ParserUtil.optionsOnlyContains(options)) {
            return new TasksOnCommandParser().parse(args);
        }

        if (ParserUtil.optionsOnlyContains(options, PREFIX_OPTION_PATIENT_INDEX)
                && options.getValue(PREFIX_OPTION_PATIENT_INDEX).get().equals(SPECIAL_CHARACTER_ALL)) {
            return new ListTaskCommand();
        }

        if (ParserUtil.optionsOnlyContains(options, PREFIX_OPTION_PATIENT_INDEX)) {
            return new ViewTaskCommandParser().parse(
                    options.getValue(PREFIX_OPTION_PATIENT_INDEX).get() + " " + args);
        }

        throw new ParseException(ParserUtil.MESSAGE_INVALID_OPTIONS);
    }
}

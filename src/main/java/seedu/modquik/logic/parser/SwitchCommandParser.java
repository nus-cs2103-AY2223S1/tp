package seedu.modquik.logic.parser;

import static seedu.modquik.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_FIELD;

import java.util.Objects;

import seedu.modquik.logic.commands.SwitchCommand;
import seedu.modquik.logic.parser.exceptions.ParseException;
import seedu.modquik.model.ModelType;


/**
 * Parses input arguments and creates a new SwitchCommand object
 */
public class SwitchCommandParser implements Parser<SwitchCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SwitchCommand
     * and returns a SwitchCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SwitchCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_FIELD);

        ParserUtil.assertAllPrefixesPresent(argMultimap, SwitchCommand.MESSAGE_USAGE,
                PREFIX_FIELD);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SwitchCommand.MESSAGE_USAGE));
        }

        String field = argMultimap.getValue(PREFIX_FIELD).get();
        Objects.requireNonNull(field);
        String trimmedField = field.trim();
        ModelType modelType;

        switch (trimmedField) {
        case "student":
            modelType = ModelType.STUDENT;
            break;
        case "tutorial":
            modelType = ModelType.TUTORIAL;
            break;
        case "consultation":
            modelType = ModelType.CONSULTATION;
            break;
        case "grade":
            modelType = ModelType.GRADE_CHART;
            break;
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwitchCommand.MESSAGE_USAGE));
        }

        return new SwitchCommand(modelType);
    }
}

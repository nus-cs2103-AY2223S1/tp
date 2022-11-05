package seedu.studmap.logic.parser;

import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_PARTICIPATION;
import static seedu.studmap.logic.parser.ParserUtil.separatePreamble;

import seedu.studmap.commons.core.index.IndexListGenerator;
import seedu.studmap.logic.commands.EditStudentCommand;
import seedu.studmap.logic.commands.ParticipateCommand;
import seedu.studmap.logic.parser.exceptions.ParseException;
import seedu.studmap.model.student.Participation;

/**
 * Parses input arguments and creates a new ParticipateCommand object
 */
public class ParticipateCommandParser extends EditStudentCommandParser
                                                      <ParticipateCommand.ParticipateCommandStudentEditor> {


    public static final String OPTION_PARTICIPATED = "yes";
    public static final String OPTION_NOT_PARTICIPATED = "no";
    public static final String MESSAGE_INVALID_OPTION =
            "Option must either be " + OPTION_PARTICIPATED + " or " + OPTION_NOT_PARTICIPATED + " for participation";

    @Override
    public Prefix[] getPrefixes() {
        return new Prefix[]{PREFIX_PARTICIPATION};
    }

    @Override
    public String getUsageMessage() {
        return ParticipateCommand.MESSAGE_USAGE;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the ParticipateCommand
     * and returns a ParticipateCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditStudentCommand<ParticipateCommand.ParticipateCommandStudentEditor> getIndexCommand(
            ArgumentMultimap argMultimap, IndexListGenerator indexListGenerator) throws ParseException {

        String[] preamble = separatePreamble(argMultimap.getPreamble());

        ParticipateCommand.ParticipateCommandStudentEditor editor = null;

        Participation.Status participated = parseOption(preamble[1]);

        if (argMultimap.getValue(PREFIX_PARTICIPATION).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, getUsageMessage()));
        }

        String participationComponent = ParserUtil
                .parseParticipationComponent(argMultimap.getValue(PREFIX_PARTICIPATION).orElse(""));
        Participation participation = new Participation(participationComponent, participated);
        editor = new ParticipateCommand.ParticipateCommandStudentEditor(participation);

        return new ParticipateCommand(indexListGenerator, editor);

    }

    private Participation.Status parseOption(String option) throws ParseException {
        switch (option) {
        case OPTION_PARTICIPATED:
            return Participation.Status.PARTICIPATED;
        case OPTION_NOT_PARTICIPATED:
            return Participation.Status.NOT_PARTICIPATED;
        default:
            throw new ParseException(MESSAGE_INVALID_OPTION);
        }
    }

    @Override
    public void validatePreamble(String preamble) throws ParseException {
        if (separatePreamble(preamble).length != 2) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }
}

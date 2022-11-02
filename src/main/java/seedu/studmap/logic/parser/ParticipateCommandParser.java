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

    public static final String MESSAGE_INVALID_OPTION = "Option must either be 'yes' or 'no' for participation";


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

        boolean participated = parseOption(preamble[1]);
        String participationComponent = ParserUtil
                .parseParticipationComponent(argMultimap.getValue(PREFIX_PARTICIPATION).orElse(""));
        Participation participation = new Participation(participationComponent, participated);
        editor = new ParticipateCommand.ParticipateCommandStudentEditor(participation);

        return new ParticipateCommand(indexListGenerator, editor);

    }

    private boolean parseOption(String option) throws ParseException {
        if (option.equals("yes")) {
            return true;
        } else if (option.equals("no")) {
            return false;
        } else {
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

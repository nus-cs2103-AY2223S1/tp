package seedu.studmap.logic.parser;

import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_PARTICIPATION;

import seedu.studmap.commons.core.index.IndexListGenerator;
import seedu.studmap.logic.commands.EditStudentCommand;
import seedu.studmap.logic.commands.UnparticipateCommand;
import seedu.studmap.logic.parser.exceptions.ParseException;
import seedu.studmap.model.student.Participation;

/**
 * Parses input arguments and creates a new UnparticipateCommand object
 */
public class UnparticipateCommandParser extends EditStudentCommandParser<UnparticipateCommand
        .UnparticipateCommandStudentEditor> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnparticipateCommand
     * and returns an UnparticipateCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditStudentCommand<UnparticipateCommand.UnparticipateCommandStudentEditor> getIndexCommand(
            ArgumentMultimap argMultimap, IndexListGenerator indexListGenerator) throws ParseException {

        String[] preamble = argMultimap.getPreamble().split("\\s+");
        if (preamble.length != 1) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        UnparticipateCommand.UnparticipateCommandStudentEditor editor = null;

        String participationComponent = ParserUtil
                .parseParticipationComponent(argMultimap.getValue(PREFIX_PARTICIPATION).orElse(""));
        Participation participation = new Participation(participationComponent, Participation.Status.PARTICIPATED);
        editor = new UnparticipateCommand.UnparticipateCommandStudentEditor(participation);

        return new UnparticipateCommand(indexListGenerator, editor);
    }

    @Override
    public Prefix[] getPrefixes() {
        return new Prefix[]{PREFIX_PARTICIPATION};
    }

    @Override
    public String getUsageMessage() {
        return UnparticipateCommand.MESSAGE_USAGE;
    }
}

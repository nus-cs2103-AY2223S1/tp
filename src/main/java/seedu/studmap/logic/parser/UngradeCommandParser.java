package seedu.studmap.logic.parser;

import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;

import seedu.studmap.commons.core.index.IndexListGenerator;
import seedu.studmap.logic.commands.EditStudentCommand;
import seedu.studmap.logic.commands.UngradeCommand;
import seedu.studmap.logic.parser.exceptions.ParseException;
import seedu.studmap.model.student.Assignment;

/**
 * Parses input arguments and creates a new UngradeCommand object
 */
public class UngradeCommandParser extends EditStudentCommandParser<UngradeCommand.UngradeCommandStudentEditor> {

    /**
     * Parses the given {@code String} of arguments in the context of the UngradeCommand
     * and returns an UngradeCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditStudentCommand<UngradeCommand.UngradeCommandStudentEditor> getIndexCommand(
        ArgumentMultimap argMultimap, IndexListGenerator indexListGenerator) throws ParseException {

        String[] preamble = argMultimap.getPreamble().split("\\s+");
        if (preamble.length != 1) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        String assignmentName = ParserUtil.parseAssignmentName(
                argMultimap.getValue(PREFIX_ASSIGNMENT).orElse(""));
        Assignment assignment = new Assignment(assignmentName, Assignment.Status.NEW);
        UngradeCommand.UngradeCommandStudentEditor editor =
                new UngradeCommand.UngradeCommandStudentEditor(assignment);

        return new UngradeCommand(indexListGenerator, editor);
    }

    @Override
    public Prefix[] getPrefixes() {
        return new Prefix[]{PREFIX_ASSIGNMENT};
    }

    @Override
    public String getUsageMessage() {
        return UngradeCommand.MESSAGE_USAGE;
    }
}

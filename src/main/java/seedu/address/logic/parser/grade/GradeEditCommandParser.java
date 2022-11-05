package seedu.address.logic.parser.grade;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;

import java.util.logging.Level;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.grade.GradeEditCommand;
import seedu.address.logic.commands.grade.GradeEditCommand.EditGradeDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;



/**
 * Parses input arguments and creates a new EditCommand object
 */
public class GradeEditCommandParser implements Parser<GradeEditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GradeEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer
                        .tokenize(args, PREFIX_GRADE);

        Index studentIndex;
        Index taskIndex;

        try {
            String[] before = argMultimap.getPreamble().split(" ");
            studentIndex = ParserUtil.parseIndex(before[0]);
            taskIndex = ParserUtil.parseIndex(before[1]);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GradeEditCommand.MESSAGE_USAGE), pe);
        }

        EditGradeDescriptor editGradeDescriptor = new EditGradeDescriptor();
        if (argMultimap.getValue(PREFIX_GRADE).isPresent()) {
            editGradeDescriptor.setGrade(ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get()));
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GradeEditCommand.MESSAGE_USAGE));
        }

        LogsCenter.getLogger(GradeEditCommandParser.class).log(Level.INFO, "Prefix grade parsed as {0}",
                argMultimap.getValue(PREFIX_GRADE));

        return new GradeEditCommand(studentIndex, taskIndex, editGradeDescriptor);
    }

}

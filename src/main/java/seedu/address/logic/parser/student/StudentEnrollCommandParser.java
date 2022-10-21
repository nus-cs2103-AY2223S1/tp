package seedu.address.logic.parser.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.student.StudentEnrollCommand;
import seedu.address.logic.commands.student.StudentEnrollCommand.EditStudentDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;




/**
 * Parses input arguments and creates a new EditCommand object
 */
public class StudentEnrollCommandParser implements Parser<StudentEnrollCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public StudentEnrollCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer
                        .tokenize(args, PREFIX_TUTORIAL_GROUP);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    StudentEnrollCommand.MESSAGE_USAGE), pe);
        }

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();

        if (argMultimap.getValue(PREFIX_TUTORIAL_GROUP).isPresent()) {
            editStudentDescriptor.setTutorialGroup(ParserUtil.parseTutorialGroup(argMultimap
                    .getValue(PREFIX_TUTORIAL_GROUP).get()));
        }


        if (!editStudentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(StudentEnrollCommand.MESSAGE_NOT_EDITED);
        }

        return new StudentEnrollCommand(index, editStudentDescriptor);
    }

}

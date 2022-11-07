package seedu.address.logic.parser.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.student.StudentExpelCommand;
import seedu.address.logic.commands.student.StudentExpelCommand.EditStudentDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.TutorialGroup;



/**
 * Parses input arguments and creates a new StudentExpelCommand object
 */
public class StudentExpelCommandParser implements Parser<StudentExpelCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the StudentExpelCommand
     * and returns an StudentExpelCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public StudentExpelCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer
                        .tokenize(args, PREFIX_TUTORIAL_GROUP);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    StudentExpelCommand.MESSAGE_USAGE), pe);
        }

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();

        TutorialGroup tutorialGroup = null;

        if (argMultimap.getValue(PREFIX_TUTORIAL_GROUP).isPresent()) {
            tutorialGroup = ParserUtil.parseTutorialGroup(argMultimap
                    .getValue(PREFIX_TUTORIAL_GROUP).get());

            editStudentDescriptor.resetTutorialGroup(tutorialGroup);
        }

        if (!editStudentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(StudentExpelCommand.MESSAGE_NOT_EDITED);
        }


        return new StudentExpelCommand(index, editStudentDescriptor);
    }

}

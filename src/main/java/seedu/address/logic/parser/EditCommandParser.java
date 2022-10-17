package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADEPROGRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_PLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_LESSON_PLAN,
                        PREFIX_HOMEWORK, PREFIX_GRADEPROGRESS, PREFIX_ATTENDANCE, PREFIX_SESSION, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_LESSON_PLAN).isPresent()) {
            editPersonDescriptor.setLessonPlan(ParserUtil.parseLessonPlan(
                    argMultimap.getValue(PREFIX_LESSON_PLAN).get()));
        }
        if (argMultimap.getValue(PREFIX_HOMEWORK).isPresent()) {
            String[] homeworkArgs = ParserUtil.parseHomeworkInfo(argMultimap.getValue(PREFIX_HOMEWORK).get());
            editPersonDescriptor.setHomeworkIndex(ParserUtil.parseIndex(homeworkArgs[0]));
            editPersonDescriptor.setHomework(ParserUtil.parseHomework(homeworkArgs[1]));
        }
        if (argMultimap.getValue(PREFIX_GRADEPROGRESS).isPresent()) {
            String[] gradeProgressArgs = ParserUtil
                .parseGradeProgressInfo(argMultimap.getValue(PREFIX_GRADEPROGRESS).get());
            editPersonDescriptor.setGradeProgressIndex(ParserUtil.parseIndex(gradeProgressArgs[0]));
            editPersonDescriptor.setGradeProgress(ParserUtil.parseGradeProgress(gradeProgressArgs[1]));
        }
        if (argMultimap.getValue(PREFIX_ATTENDANCE).isPresent()) {
            String[] attendanceArgs = ParserUtil.parseAttendanceInfo(argMultimap.getValue(PREFIX_ATTENDANCE).get());
            editPersonDescriptor.setAttendanceIndex(ParserUtil.parseIndex(attendanceArgs[0]));
            editPersonDescriptor.setAttendance(ParserUtil.parseAttendance(attendanceArgs[1]));
        }
        if (argMultimap.getValue(PREFIX_SESSION).isPresent()) {
            String[] sessionArgs = ParserUtil.parseSessionInfo(argMultimap.getValue(PREFIX_SESSION).get());
            editPersonDescriptor.setSessionIndex(ParserUtil.parseIndex(sessionArgs[0]));
            editPersonDescriptor.setSession(ParserUtil.parseSession(sessionArgs[1]));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}

package seedu.modquik.logic.parser.student;

import static java.util.Objects.requireNonNull;
import static seedu.modquik.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_PARTICIPATION;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.modquik.commons.core.index.Index;
import seedu.modquik.logic.commands.student.EditStudentCommand;
import seedu.modquik.logic.commands.student.EditStudentCommand.EditPersonDescriptor;
import seedu.modquik.logic.parser.ArgumentMultimap;
import seedu.modquik.logic.parser.ArgumentTokenizer;
import seedu.modquik.logic.parser.Parser;
import seedu.modquik.logic.parser.ParserUtil;
import seedu.modquik.logic.parser.exceptions.ParseException;
import seedu.modquik.logic.parser.tutorial.TutorialParserUtil;
import seedu.modquik.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditStudentCommandParser implements Parser<EditStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditStudentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_TELEGRAM, PREFIX_MODULE, PREFIX_TUTORIAL,
                        PREFIX_ATTENDANCE, PREFIX_PARTICIPATION, PREFIX_GRADE, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, EditStudentCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(StudentParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            editPersonDescriptor.setId(StudentParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(StudentParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(StudentParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_TELEGRAM).isPresent()) {
            editPersonDescriptor.setTelegram(StudentParserUtil.parseTelegram(
                    argMultimap.getValue(PREFIX_TELEGRAM).get()));
        }
        if (argMultimap.getValue(PREFIX_MODULE).isPresent()) {
            editPersonDescriptor.setTutorialModule(ParserUtil
                    .parseModuleCode(argMultimap.getValue(PREFIX_MODULE).get()));
        }
        if (argMultimap.getValue(PREFIX_TUTORIAL).isPresent()) {
            editPersonDescriptor.setTutorialName(TutorialParserUtil
                    .parseTutorialName(argMultimap.getValue(PREFIX_TUTORIAL).get()));
        }
        if (argMultimap.getValue(PREFIX_ATTENDANCE).isPresent()) {
            editPersonDescriptor.setAttendance(StudentParserUtil
                    .parseAttendance(argMultimap.getValue(PREFIX_ATTENDANCE).get()));
        }
        if (argMultimap.getValue(PREFIX_PARTICIPATION).isPresent()) {
            editPersonDescriptor.setParticipation(StudentParserUtil
                    .parseParticipation(argMultimap.getValue(PREFIX_PARTICIPATION).get()));
        }
        if (argMultimap.getValue(PREFIX_GRADE).isPresent()) {
            editPersonDescriptor.setGrade(StudentParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditStudentCommand.MESSAGE_NOT_EDITED);
        }

        return new EditStudentCommand(index, editPersonDescriptor);
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
        return Optional.of(StudentParserUtil.parseTags(tagSet));
    }

}

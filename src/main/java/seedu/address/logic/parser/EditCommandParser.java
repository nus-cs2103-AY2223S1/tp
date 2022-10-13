package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTITUTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXTOFKIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditDescriptor;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.logic.commands.EditCommand.EditTuitionClassDescriptor;
import seedu.address.logic.commands.EditCommand.EditTutorDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    private Model.ListType listType;

    public EditCommandParser(Model.ListType listType) {
        this.listType = listType;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SUBJECT, PREFIX_DAY, PREFIX_TIME, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_SCHOOL, PREFIX_NEXTOFKIN,
                        PREFIX_LEVEL, PREFIX_QUALIFICATION, PREFIX_INSTITUTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.getMessageUsage(listType)), pe);
        }

        EditDescriptor editDescriptor;

        switch (listType) {
        case STUDENT_LIST:
            editDescriptor = extractFromMapForStudent(argMultimap);
            break;

        case TUTOR_LIST:
            editDescriptor = extractFromMapForTutor(argMultimap);
            break;

        case TUITIONCLASS_LIST:
            editDescriptor = extractFromMapForClass(argMultimap);
            break;

        default:
            editDescriptor = extractFromMapForPerson(argMultimap);

        }

        //check if no change then throw
        if (!editDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        return new EditCommand(index, editDescriptor);

    }

    private EditPersonDescriptor extractFromMapForPerson(ArgumentMultimap argMultimap) throws ParseException {
        if (areAnyPrefixesPresent(argMultimap, PREFIX_SUBJECT, PREFIX_DAY, PREFIX_TIME)) {
            throw new ParseException(
                    String.format(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.getMessageUsage(listType))));
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parsePersonName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);
        return editPersonDescriptor;
    }

    private EditStudentDescriptor extractFromMapForStudent(ArgumentMultimap argMultimap) throws ParseException {
        //check if unnecessary fields are there
        if (areAnyPrefixesPresent(argMultimap,
                PREFIX_QUALIFICATION, PREFIX_INSTITUTION)) {
            throw new ParseException(
                    String.format(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.getMessageUsage(listType))));
        }

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor(extractFromMapForPerson(argMultimap));
        if (argMultimap.getValue(PREFIX_SCHOOL).isPresent()) {
            editStudentDescriptor.setSchool(ParserUtil.parseSchool(argMultimap.getValue(PREFIX_SCHOOL).get()));
        }
        if (argMultimap.getValue(PREFIX_LEVEL).isPresent()) {
            editStudentDescriptor.setLevel(ParserUtil.parseLevel(argMultimap.getValue(PREFIX_LEVEL).get()));
        }
        if (argMultimap.getValue(PREFIX_NEXTOFKIN).isPresent()) {
            editStudentDescriptor.setNextOfKin(ParserUtil.parseNextOfKin(argMultimap.getValue(PREFIX_NEXTOFKIN).get()));
        }
        return editStudentDescriptor;
    }

    private EditTutorDescriptor extractFromMapForTutor(ArgumentMultimap argMultimap) throws ParseException {
        //check if unnecessary fields are there
        if (areAnyPrefixesPresent(argMultimap,
                PREFIX_SCHOOL, PREFIX_LEVEL, PREFIX_NEXTOFKIN)) {
            throw new ParseException(
                    String.format(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.getMessageUsage(listType))));
        }

        EditTutorDescriptor editTutorDescriptor = new EditTutorDescriptor(extractFromMapForPerson(argMultimap));
        if (argMultimap.getValue(PREFIX_QUALIFICATION).isPresent()) {
            editTutorDescriptor.setQualification(
                    ParserUtil.parseQualification(argMultimap.getValue(PREFIX_QUALIFICATION).get()));
        }
        if (argMultimap.getValue(PREFIX_INSTITUTION).isPresent()) {
            editTutorDescriptor.setInstitution(
                    ParserUtil.parseInstitution(argMultimap.getValue(PREFIX_INSTITUTION).get()));
        }
        return editTutorDescriptor;
    }

    private EditTuitionClassDescriptor extractFromMapForClass(ArgumentMultimap argMultimap) throws ParseException {
        //check if unnecessary fields are there
        if (areAnyPrefixesPresent(argMultimap,
                PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_SCHOOL, PREFIX_NEXTOFKIN, PREFIX_QUALIFICATION,
                PREFIX_INSTITUTION)) {
            throw new ParseException(
                    String.format(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.getMessageUsage(listType))));
        }

        EditTuitionClassDescriptor editTuitionClassDescriptor = new EditTuitionClassDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editTuitionClassDescriptor.setName(ParserUtil.parseClassName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_SUBJECT).isPresent()) {
            editTuitionClassDescriptor.setSubject(ParserUtil.parseSubject(argMultimap.getValue(PREFIX_SUBJECT).get()));
        }
        if (argMultimap.getValue(PREFIX_LEVEL).isPresent()) {
            editTuitionClassDescriptor.setLevel(ParserUtil.parseLevel(argMultimap.getValue(PREFIX_LEVEL).get()));
        }
        if (argMultimap.getValue(PREFIX_DAY).isPresent()) {
            editTuitionClassDescriptor.setDay(ParserUtil.parseDay(argMultimap.getValue(PREFIX_DAY).get()));
        }
        if (argMultimap.getValue(PREFIX_TIME).isPresent()) {
            editTuitionClassDescriptor.setTime(ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editTuitionClassDescriptor::setTags);
        return editTuitionClassDescriptor;
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

    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

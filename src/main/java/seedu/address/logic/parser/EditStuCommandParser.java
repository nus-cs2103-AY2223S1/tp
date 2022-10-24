package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HANDLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_TA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditStuCommand;
import seedu.address.logic.commands.EditStuCommand.EditStudentDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditStuCommand object
 */
public class EditStuCommandParser implements Parser<EditStuCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditStuCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG,
                        PREFIX_ID, PREFIX_HANDLE, PREFIX_MODULE_CODE, PREFIX_STUDENT_TA, PREFIX_CLASS_GROUP);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditStuCommand.MESSAGE_USAGE), pe);
        }

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editStudentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editStudentDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editStudentDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editStudentDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            editStudentDescriptor.setId(ParserUtil.parseID(argMultimap.getValue(PREFIX_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_HANDLE).isPresent()) {
            editStudentDescriptor.setTelegramHandle(ParserUtil
                    .parseTelegramHandle(argMultimap.getValue(PREFIX_HANDLE).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editStudentDescriptor::setTags);
        parseStudentModuleInfoForEdit(argMultimap.getAllValues(PREFIX_MODULE_CODE))
                .ifPresent(editStudentDescriptor::setStudentModuleInfo);
        parseTeachingAssistantInfoForEdit(argMultimap.getAllValues(PREFIX_STUDENT_TA))
                .ifPresent(editStudentDescriptor::setTeachingAssistantInfo);
        parseClassGroupsForEdit(argMultimap.getAllValues(PREFIX_CLASS_GROUP))
                .ifPresent(editStudentDescriptor::setClassGroups);

        if (!editStudentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditStuCommand.MESSAGE_NOT_EDITED);
        }

        return new EditStuCommand(index, editStudentDescriptor);
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

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<ModuleCode>> parseStudentModuleInfoForEdit(Collection<String> moduleInfo)
            throws ParseException {
        assert moduleInfo != null;

        if (moduleInfo.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> moduleSet = moduleInfo.size() == 1
                && moduleInfo.contains("") ? Collections.emptySet() : moduleInfo;
        return Optional.of(ParserUtil.parseStudentInfo(moduleSet));
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<ModuleCode>> parseTeachingAssistantInfoForEdit(Collection<String> teachingAssistantInfo)
            throws ParseException {
        assert teachingAssistantInfo != null;

        if (teachingAssistantInfo.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> moduleSet = teachingAssistantInfo.size() == 1
                && teachingAssistantInfo.contains("") ? Collections.emptySet() : teachingAssistantInfo;
        return Optional.of(ParserUtil.parseTeachingAssistantInfo(moduleSet));
    }

    /**
     * Parses {@code Collection<String> classGroups} into a {@code Set<String>} if {@code classGroup} is non-empty.
     * If {@code classGroup} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<String>} containing zero tags.
     */
    private Optional<Set<String>> parseClassGroupsForEdit(Collection<String> classGroups)
            throws ParseException {
        assert classGroups != null;

        if (classGroups.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> classGroupsSet = classGroups.size() == 1
                && classGroups.contains("") ? Collections.emptySet() : classGroups;
        return Optional.of(ParserUtil.parseClassGroups(classGroupsSet));
    }

}

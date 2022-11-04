package seedu.hrpro.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_CONTACT;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_DEPARTMENT;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_LEAVE;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_NAME;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_TITLE;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.hrpro.commons.core.index.Index;
import seedu.hrpro.logic.commands.EditStaffCommand;
import seedu.hrpro.logic.commands.EditStaffCommand.EditStaffDescriptor;
import seedu.hrpro.logic.parser.exceptions.ParseException;
import seedu.hrpro.model.project.ProjectName;
import seedu.hrpro.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditStaffCommand object.
 *
 */
public class EditStaffCommandParser implements Parser<EditStaffCommand> {

    /**
     * Parses arguments to create an {@code EditStaffCommand} object.
     */
    public EditStaffCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PROJECT_NAME, PREFIX_STAFF_NAME, PREFIX_STAFF_CONTACT,
                        PREFIX_STAFF_DEPARTMENT, PREFIX_STAFF_LEAVE, PREFIX_STAFF_TITLE, PREFIX_TAG);

        Index index;

        if (!arePrefixesPresent(argMultimap, PREFIX_PROJECT_NAME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditStaffCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditStaffCommand.MESSAGE_USAGE), pe);
        }

        ProjectName projectName = ParserUtil.parseProjectName(argMultimap.getValue(PREFIX_PROJECT_NAME).get());

        EditStaffDescriptor editStaffDescriptor = new EditStaffDescriptor();

        if (argMultimap.getValue(PREFIX_STAFF_NAME).isPresent()) {
            editStaffDescriptor.setStaffName(ParserUtil
                .parseStaffName(argMultimap.getValue(PREFIX_STAFF_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_STAFF_CONTACT).isPresent()) {
            editStaffDescriptor.setStaffContact(ParserUtil
                .parseStaffContact(argMultimap.getValue(PREFIX_STAFF_CONTACT).get()));
        }

        if (argMultimap.getValue(PREFIX_STAFF_LEAVE).isPresent()) {
            editStaffDescriptor.setStaffLeave(ParserUtil
                .parseStaffLeave(argMultimap.getValue(PREFIX_STAFF_LEAVE).get()));
        }

        if (argMultimap.getValue(PREFIX_STAFF_TITLE).isPresent()) {
            editStaffDescriptor.setStaffTitle(ParserUtil
                .parseStaffTitle(argMultimap.getValue(PREFIX_STAFF_TITLE).get()));
        }

        if (argMultimap.getValue(PREFIX_STAFF_DEPARTMENT).isPresent()) {
            editStaffDescriptor.setStaffDepartment(ParserUtil
                .parseStaffDepartment(argMultimap.getValue(PREFIX_STAFF_DEPARTMENT).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editStaffDescriptor::setTags);

        if (!editStaffDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditStaffCommand.MESSAGE_NOT_EDITED);
        }

        return new EditStaffCommand(projectName, index, editStaffDescriptor);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
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

package seedu.watson.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.watson.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_STUDENTCLASS;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.watson.commons.core.index.Index;
import seedu.watson.logic.commands.EditCommand;
import seedu.watson.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.watson.logic.parser.exceptions.ParseException;
import seedu.watson.model.student.Remark;
import seedu.watson.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                                       PREFIX_ADDRESS, PREFIX_STUDENTCLASS, PREFIX_REMARK, PREFIX_TAG);

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
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(
                argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_STUDENTCLASS).isPresent()) {
            editPersonDescriptor.setStudentClass(ParserUtil.parseStudentClass(
                argMultimap.getValue(PREFIX_STUDENTCLASS).get()));
        }

        parseRemarksForEdit(argMultimap.getAllValues(PREFIX_REMARK))
            .ifPresent(editPersonDescriptor::setRemarks);
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG))
            .ifPresent(editPersonDescriptor::setTags);

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

    /**
     * Same implementation as parseTagsForEdit.
     * Method can be combined with parseTagsForEdit in the future if possible.
     */
    private Optional<Set<Remark>> parseRemarksForEdit(Collection<String> remarks) throws ParseException {
        assert remarks != null;

        if (remarks.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> remarkSet = remarks.size() == 1 && remarks.contains("")
                                    ? Collections.emptySet()
                                    : remarks;
        return Optional.of(ParserUtil.parseRemarks(remarkSet));
    }

}

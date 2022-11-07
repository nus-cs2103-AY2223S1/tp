package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CAP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADUATION_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIVERSITY;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS,
                        PREFIX_GENDER,
                        PREFIX_GRADUATION_DATE,
                        PREFIX_CAP,
                        PREFIX_UNIVERSITY,
                        PREFIX_MAJOR,
                        PREFIX_JOB_ID,
                        PREFIX_JOB_TITLE,
                        PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor
                    .setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor
                    .setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor
                    .setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor
                    .setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_CAP).isPresent()) {
            editPersonDescriptor
                    .setCap(ParserUtil.parseCap(argMultimap.getValue(PREFIX_CAP).get()));
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            editPersonDescriptor
                    .setGender(ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get()));
        }
        if (argMultimap.getValue(PREFIX_GRADUATION_DATE).isPresent()) {
            editPersonDescriptor
                    .setGraduationDate(ParserUtil.parseGraduationDate(
                            argMultimap.getValue(PREFIX_GRADUATION_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_UNIVERSITY).isPresent()) {
            editPersonDescriptor
                    .setUniversity(ParserUtil.parseUniversity(argMultimap.getValue(PREFIX_UNIVERSITY).get()));
        }
        if (argMultimap.getValue(PREFIX_MAJOR).isPresent()) {
            editPersonDescriptor
                    .setMajor(ParserUtil.parseMajor(argMultimap.getValue(PREFIX_MAJOR).get()));
        }
        if (argMultimap.getValue(PREFIX_JOB_ID).isPresent()) {
            editPersonDescriptor
                    .setId(ParserUtil.parseId(argMultimap.getValue(PREFIX_JOB_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_JOB_TITLE).isPresent()) {
            editPersonDescriptor
                    .setTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_JOB_TITLE).get()));
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

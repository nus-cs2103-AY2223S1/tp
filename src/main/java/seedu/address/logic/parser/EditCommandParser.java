package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AND_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AND_TIME_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UID;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DateTime;
import seedu.address.model.person.Uid;
import seedu.address.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_UID, PREFIX_CATEGORY, PREFIX_NAME, PREFIX_GENDER,
                        PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_DATE_AND_TIME, PREFIX_TAG,
                        PREFIX_DATE_AND_TIME_INDEX);

        Uid uid;

        if (argMultimap.getValue(PREFIX_UID).isPresent()) {
            uid = ParserUtil.parseUid(argMultimap.getValue(PREFIX_UID).get());
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_CATEGORY).isPresent()) {
            editPersonDescriptor.setCategory(ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get()));
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            editPersonDescriptor.setGender(ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get()));
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

        parseDatesTimesForEdit(argMultimap.getAllValues(PREFIX_DATE_AND_TIME))
                .ifPresent(editPersonDescriptor::setDatesTimes);

        parseDateTimeIndexesForEdit(argMultimap.getAllValues(PREFIX_DATE_AND_TIME_INDEX))
                .ifPresent(editPersonDescriptor::setDateTimeIndexes);

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(uid, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if
     * {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be
     * parsed into a
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
     * Parses {@code Collection<String> datesTimes} into a {@code List<DateTime>} if {@code dateTimes} is non-empty.
     * If {@code datesTimes} contain only one element which is an empty string, it will be parsed into a
     * {@code List<DateTime>} containing zero dateTime.
     */
    private Optional<List<DateTime>> parseDatesTimesForEdit(Collection<String> datesTimes) throws ParseException {
        assert datesTimes != null;

        if (datesTimes.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> dateTimeList = datesTimes.size() == 1 && datesTimes.contains("")
                ? Collections.emptyList() : datesTimes;
        return Optional.of(ParserUtil.parseDatesTimes(dateTimeList));
    }

    /**
     * Parses {@code Collection<String> dateTimeIndexes} into a {@code List<Integer>}
     * if {@code dateTimeIndexes} is non-empty.
     */
    private Optional<List<Index>> parseDateTimeIndexesForEdit(Collection<String> dateTimeIndexes)
            throws ParseException {
        assert dateTimeIndexes != null;

        if (dateTimeIndexes.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> dateTimeIndexList = dateTimeIndexes.size() == 1 && dateTimeIndexes.contains("")
                ? Collections.emptyList() : dateTimeIndexes;
        return Optional.of(ParserUtil.parseDateTimesIndexes(dateTimeIndexList));
    }

}


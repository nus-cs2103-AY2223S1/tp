package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AND_SLOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AND_SLOT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNAVAILABLE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNAVAILABLE_DATE_INDEX;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Date;
import seedu.address.model.person.DateSlot;
import seedu.address.model.person.Uid;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_UID, PREFIX_CATEGORY, PREFIX_NAME,
                PREFIX_GENDER,
                PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_DATE_AND_SLOT, PREFIX_TAG,
                PREFIX_DATE_AND_SLOT_INDEX, PREFIX_UNAVAILABLE_DATE, PREFIX_UNAVAILABLE_DATE_INDEX);

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

        parseDatesSlotsForEdit(argMultimap.getAllValues(PREFIX_DATE_AND_SLOT))
                .ifPresent(editPersonDescriptor::setDatesSlots);

        parseDateSlotIndexesForEdit(argMultimap.getAllValues(PREFIX_DATE_AND_SLOT_INDEX))
                .ifPresent(editPersonDescriptor::setDateSlotIndexes);

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);
        parseDatesForEdit(argMultimap.getAllValues(PREFIX_UNAVAILABLE_DATE))
                .ifPresent(editPersonDescriptor::setUnavailableDates);
        parseDateIndexesForEdit(argMultimap.getAllValues(PREFIX_UNAVAILABLE_DATE_INDEX))
                .ifPresent(editPersonDescriptor::setDateIndexes);

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
     * Parses {@code Collection<String> datesSlots} into a {@code List<DateSlot>} if
     * {@code dateSlots} is non-empty.
     * If {@code datesSlots} contain only one element which is an empty string, it
     * will be parsed into a
     * {@code List<DateSlot>} containing zero dateTime.
     */
    private Optional<List<DateSlot>> parseDatesSlotsForEdit(Collection<String> datesSlots) throws ParseException {
        assert datesSlots != null;

        if (datesSlots.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> dateSlotList = datesSlots.size() == 1 && datesSlots.contains("")
                ? Collections.emptyList()
                : datesSlots;

        return Optional.of(ParserUtil.parseDatesSlots(dateSlotList));
    }

    /**
     * Parses {@code Collection<String> dateSlotIndexes} into a
     * {@code List<Integer>}
     * if {@code dateSlotIndexes} is non-empty.
     */
    private Optional<List<Index>> parseDateSlotIndexesForEdit(Collection<String> dateSlotIndexes)
            throws ParseException {
        assert dateSlotIndexes != null;

        if (dateSlotIndexes.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> dateSlotIndexList = dateSlotIndexes.size() == 1 && dateSlotIndexes.contains("")
                ? Collections.emptyList()
                : dateSlotIndexes;
        return Optional.of(ParserUtil.parseIndexes(dateSlotIndexList));
    }

    /**
     * Parses {@code Collection<String> dates} into a {@code List<Date>} if
     * {@code date} is non-empty.
     * If {@code dates} contain only one element which is an empty string, it will
     * be parsed into a
     * {@code List<Date>} containing zero date.
     */
    private Optional<List<Date>> parseDatesForEdit(Collection<String> dates) throws ParseException {
        assert dates != null;

        if (dates.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> dateList = dates.size() == 1 && dates.contains("")
                ? Collections.emptyList()
                : dates;

        return Optional.of(ParserUtil.parseDates(dateList));
    }

    /**
     * Parses {@code Collection<String> dateIndexes} into a {@code List<Integer>}
     * if {@code dateIndexes} is non-empty.
     */
    private Optional<List<Index>> parseDateIndexesForEdit(Collection<String> dateIndexes)
            throws ParseException {
        assert dateIndexes != null;

        if (dateIndexes.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> dateIndexList = dateIndexes.size() == 1 && dateIndexes.contains("")
                ? Collections.emptyList()
                : dateIndexes;
        return Optional.of(ParserUtil.parseIndexes(dateIndexList));
    }

}

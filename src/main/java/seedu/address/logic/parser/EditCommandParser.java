package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.portfolio.Note;
import seedu.address.model.portfolio.Plan;
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
            ArgumentTokenizer.tokenize(
                args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_INCOME, PREFIX_MEETING_DATE,
                PREFIX_MEETING_LOCATION, PREFIX_TAG, PREFIX_RISK, PREFIX_PLAN, PREFIX_NOTE);

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
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_INCOME).isPresent()) {
            editPersonDescriptor.setIncome(ParserUtil.parseIncome(argMultimap.getValue(PREFIX_INCOME).get()));
        }
        if (argMultimap.getValue(PREFIX_MEETING_DATE).isPresent()) {
            editPersonDescriptor.setMeetingDate(
                ParserUtil.parseMeetingDate(argMultimap.getValue(PREFIX_MEETING_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_MEETING_LOCATION).isPresent()) {
            editPersonDescriptor.setMeetingLocation(
                ParserUtil.parseMeetingLocation(argMultimap.getValue(PREFIX_MEETING_LOCATION).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        if (argMultimap.getValue(PREFIX_RISK).isPresent()) {
            editPersonDescriptor.setRisk(
                ParserUtil.parseRisk(argMultimap.getValue(PREFIX_RISK).get()));
        }

        parsePlansForEdit(argMultimap.getAllValues(PREFIX_PLAN)).ifPresent(editPersonDescriptor::setPlans);

        parseNotesForEdit(argMultimap.getAllValues(PREFIX_NOTE)).ifPresent(editPersonDescriptor::setNotes);

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
    public static Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Parses {@code Collection<String> plans} into a {@code Set<Plan>} if {@code plans} is non-empty.
     * If {@code plans} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Plan>} containing zero plans.
     */
    public static Optional<Set<Plan>> parsePlansForEdit(Collection<String> plans) throws ParseException {
        assert plans != null;

        if (plans.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> planSet = plans.size() == 1 && plans.contains("") ? Collections.emptySet() : plans;
        return Optional.of(ParserUtil.parsePlans(planSet));
    }

    /**
     * Parses {@code Collection<String> notes} into a {@code Set<Note>} if {@code notes} is non-empty.
     * If {@code notes} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<note>} containing zero note.
     */
    public static Optional<Set<Note>> parseNotesForEdit(Collection<String> notes) throws ParseException {
        assert notes != null;

        if (notes.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> notesSet = notes.size() == 1 && notes.contains("") ? Collections.emptySet() : notes;
        return Optional.of(ParserUtil.parseNotes(notesSet));
    }

}

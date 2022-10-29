package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDITIONAL_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDITIONAL_NOTES_APPEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONEY_OWED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONEY_PAID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOK_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATES_PER_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_NOK_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_CLASS_DATE_TIME, PREFIX_MONEY_OWED, PREFIX_MONEY_PAID,
                PREFIX_RATES_PER_CLASS, PREFIX_ADDITIONAL_NOTES, PREFIX_ADDITIONAL_NOTES_APPEND, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editStudentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editStudentDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_NOK_PHONE).isPresent()) {
            editStudentDescriptor.setNokPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_NOK_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editStudentDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editStudentDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_CLASS_DATE_TIME).isPresent()) {
            editStudentDescriptor.setClass(ParserUtil.parseClass(argMultimap.getValue(PREFIX_CLASS_DATE_TIME).get()));
        }
        if (argMultimap.getValue(PREFIX_MONEY_OWED).isPresent()) {
            editStudentDescriptor.setMoneyOwed(ParserUtil.parseMoney(argMultimap.getValue(PREFIX_MONEY_OWED).get()));
        }
        if (argMultimap.getValue(PREFIX_MONEY_PAID).isPresent()) {
            editStudentDescriptor.setMoneyPaid(ParserUtil.parseMoney(argMultimap.getValue(PREFIX_MONEY_PAID).get()));
        }
        if (argMultimap.getValue(PREFIX_RATES_PER_CLASS).isPresent()) {
            editStudentDescriptor.setRatesPerClass(
                    ParserUtil.parseMoney(argMultimap.getValue(PREFIX_RATES_PER_CLASS).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDITIONAL_NOTES).isPresent()) {
            editStudentDescriptor.setAdditionalNotes(
                    ParserUtil.parseAdditionalNotes(argMultimap.getValue(PREFIX_ADDITIONAL_NOTES).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDITIONAL_NOTES_APPEND).isPresent()) {
            editStudentDescriptor.setAppendedAdditionalNotes(
                    ParserUtil.parseAdditionalNotes(argMultimap.getValue(PREFIX_ADDITIONAL_NOTES_APPEND).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editStudentDescriptor::setTags);

        if (!editStudentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        return new EditCommand(index, editStudentDescriptor);
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

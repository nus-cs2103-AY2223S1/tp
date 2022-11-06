package seedu.boba.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.boba.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_BIRTHDAY_MONTH;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_REWARD;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.boba.logic.commands.EditCommand;
import seedu.boba.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.boba.logic.parser.exceptions.ParseException;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.Phone;
import seedu.boba.model.customer.Reward;
import seedu.boba.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements CommandParser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                    PREFIX_BIRTHDAY_MONTH, PREFIX_REWARD, PREFIX_TAG);
        Phone phoneIdentifier = null;
        Email emailIdentifier = null;
        Prefix firstPrefix = argMultimap.getFirstPrefix();

        try {
            if (!(firstPrefix.equals(PREFIX_PHONE) || firstPrefix.equals(PREFIX_EMAIL))) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
            } else if (argMultimap.getPhoneIdentifier()) {
                phoneIdentifier = ParserUtil.parsePhone(argMultimap.getAllValues(PREFIX_PHONE).get(0));
            } else if (argMultimap.getEmailIdentifier()) {
                emailIdentifier = ParserUtil.parseEmail(argMultimap.getAllValues(PREFIX_EMAIL).get(0));
            }
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getPrefixCount() <= 2) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            if (argMultimap.getAllValues(PREFIX_NAME).size() == 1) {
                editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
            }
        }
        if (!argMultimap.getAllValues(PREFIX_PHONE).isEmpty()) {
            if (argMultimap.getAllValues(PREFIX_PHONE).size() == 1) {
                editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getAllValues(PREFIX_PHONE).get(0)));
            } else if (argMultimap.getAllValues(PREFIX_PHONE).size() == 2) {
                editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getAllValues(PREFIX_PHONE).get(1)));
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
            }
        }
        if (!argMultimap.getAllValues(PREFIX_EMAIL).isEmpty()) {
            if (argMultimap.getAllValues(PREFIX_EMAIL).size() == 1) {
                editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getAllValues(PREFIX_EMAIL).get(0)));
            } else if (argMultimap.getAllValues(PREFIX_EMAIL).size() == 2) {
                editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getAllValues(PREFIX_EMAIL).get(1)));
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
            }
        }
        if (argMultimap.getValue(PREFIX_BIRTHDAY_MONTH).isPresent()) {
            if (argMultimap.getAllValues(PREFIX_BIRTHDAY_MONTH).size() == 1) {
                editPersonDescriptor.setBirthdayMonth(
                        ParserUtil.parseBirthdayMonth(argMultimap.getValue(PREFIX_BIRTHDAY_MONTH).get()));
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
            }
        }

        if (argMultimap.getValue(PREFIX_REWARD).isPresent()) {
            try {
                int newReward = Integer.parseInt(argMultimap.getValue(PREFIX_REWARD).get());
                if (newReward < 0) {
                    throw new ParseException(Reward.MESSAGE_CONSTRAINTS);
                }
                if (argMultimap.getAllValues(PREFIX_REWARD).size() == 1) {
                    editPersonDescriptor.setReward(ParserUtil.parseReward(String.valueOf(newReward)));
                } else {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
                }
            } catch (NumberFormatException e) {
                //throw new ParseException("Reward.MESSAGE_MAX_INTEGER");
                throw new ParseException(Reward.MESSAGE_MAX_INTEGER);
            }

        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return argMultimap.getPhoneIdentifier()
                ? new EditCommand(phoneIdentifier, editPersonDescriptor)
                : new EditCommand(emailIdentifier, editPersonDescriptor);
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

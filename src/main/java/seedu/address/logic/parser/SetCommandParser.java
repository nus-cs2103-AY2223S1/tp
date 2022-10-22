package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMEZONE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.SetCommand;
import seedu.address.logic.commands.SetCommand.SetPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.contact.ContactType;
import seedu.address.model.tag.Tag;

/**
 * Parses the arguments supplied and returns a SetCommand object.
 */
public class SetCommandParser implements Parser<SetCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SetCommand
     * and returns an SetCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public SetCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EMAIL, PREFIX_PHONE, PREFIX_SLACK, PREFIX_TELEGRAM,
                                                 PREFIX_ADDRESS, PREFIX_NAME, PREFIX_TAG, PREFIX_ROLE,
                                                 PREFIX_TIMEZONE);

        if (!anyPrefixesPresent(argMultimap, PREFIX_EMAIL, PREFIX_PHONE, PREFIX_SLACK, PREFIX_TELEGRAM,
                                             PREFIX_ADDRESS, PREFIX_NAME, PREFIX_TAG, PREFIX_ROLE,
                                             PREFIX_TIMEZONE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetCommand.MESSAGE_USAGE));
        }

        SetPersonDescriptor setPersonDescriptor = new SetPersonDescriptor();
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            setPersonDescriptor.setContact(ContactType.EMAIL,
                                                ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            setPersonDescriptor.setContact(ContactType.PHONE,
                    ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }

        if (argMultimap.getValue(PREFIX_SLACK).isPresent()) {
            setPersonDescriptor.setContact(ContactType.SLACK,
                    ParserUtil.parseSlack(argMultimap.getValue(PREFIX_SLACK).get()));
        }

        if (argMultimap.getValue(PREFIX_TELEGRAM).isPresent()) {
            setPersonDescriptor.setContact(ContactType.TELEGRAM,
                    ParserUtil.parseTelegram(argMultimap.getValue(PREFIX_TELEGRAM).get()));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            setPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            setPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }

        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            setPersonDescriptor.setRole(ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get()));
        }

        if (argMultimap.getValue(PREFIX_TIMEZONE).isPresent()) {
            setPersonDescriptor.setTimezone(ParserUtil.parseTimezone(argMultimap.getValue(PREFIX_TIMEZONE).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(setPersonDescriptor::setTags);
        return new SetCommand(setPersonDescriptor);
    }

    /**
     * Returns true if one of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
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

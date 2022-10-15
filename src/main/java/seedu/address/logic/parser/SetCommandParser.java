package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.stream.Stream;

import seedu.address.logic.commands.SetCommand;
import seedu.address.logic.commands.SetCommand.SetContactDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.contact.ContactType;

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
                ArgumentTokenizer.tokenize(args, PREFIX_EMAIL, PREFIX_PHONE, PREFIX_SLACK, PREFIX_TELEGRAM);

        if (!arePrefixesPresent(argMultimap, PREFIX_EMAIL, PREFIX_PHONE, PREFIX_SLACK, PREFIX_TELEGRAM)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetCommand.MESSAGE_USAGE));
        }

        SetContactDescriptor setContactDescriptor = new SetContactDescriptor();
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            setContactDescriptor.updateContacts(ContactType.EMAIL,
                                                ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            setContactDescriptor.updateContacts(ContactType.PHONE,
                    ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }

        if (argMultimap.getValue(PREFIX_SLACK).isPresent()) {
            setContactDescriptor.updateContacts(ContactType.SLACK,
                    ParserUtil.parseSlack(argMultimap.getValue(PREFIX_SLACK).get()));
        }

        if (argMultimap.getValue(PREFIX_TELEGRAM).isPresent()) {
            setContactDescriptor.updateContacts(ContactType.TELEGRAM,
                    ParserUtil.parseTelegram(argMultimap.getValue(PREFIX_TELEGRAM).get()));
        }
        return new SetCommand(setContactDescriptor);
    }

    /**
     * Returns true if one of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

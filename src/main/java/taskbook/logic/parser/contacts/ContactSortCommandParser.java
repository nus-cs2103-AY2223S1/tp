package taskbook.logic.parser.contacts;

import java.util.regex.Pattern;
import java.util.stream.Stream;

import taskbook.commons.core.Messages;
import taskbook.logic.commands.contacts.ContactSortAddedChronologicalCommand;
import taskbook.logic.commands.contacts.ContactSortCommand;
import taskbook.logic.commands.contacts.ContactSortNameAlphabeticalCommand;
import taskbook.logic.commands.contacts.ContactSortNameReverseAlphabeticalCommand;
import taskbook.logic.commands.contacts.ContactSortPhoneAscendingCommand;
import taskbook.logic.commands.contacts.ContactSortPhoneDescendingCommand;
import taskbook.logic.commands.tasks.TaskSortCommand;
import taskbook.logic.parser.ArgumentMultimap;
import taskbook.logic.parser.ArgumentTokenizer;
import taskbook.logic.parser.CliSyntax;
import taskbook.logic.parser.Parser;
import taskbook.logic.parser.Prefix;
import taskbook.logic.parser.contacts.enums.SortTypes;
import taskbook.logic.parser.exceptions.ParseException;
/**
 * Parses input arguments and creates a new ContactSortCommand.
 */
public class ContactSortCommandParser implements Parser<ContactSortCommand> {
    // Note: the space at the start of the arguments is necessary due to ArgumentTokenizer behavior.
    private static final Pattern SORT =
            Pattern.compile(String.format("\\s+%s.*", CliSyntax.PREFIX_SORT_TYPE.getPrefix()));

    /**
     * Parses the given {@code String} of arguments in the context of the TaskSortCommand
     * and returns an TaskSortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ContactSortCommand parse(String args) throws ParseException {
        if (args.contains(CliSyntax.PREFIX_ASSIGN_FROM.getPrefix())
                && args.contains(CliSyntax.PREFIX_ASSIGN_TO.getPrefix())) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ContactSortCommand.MESSAGE_USAGE));
        }

        if (SORT.matcher(args).matches()) {
            return parseWithPrefix(args, CliSyntax.PREFIX_SORT_TYPE);
        }

        throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                ContactSortCommand.MESSAGE_USAGE));
    }

    private ContactSortCommand parseWithPrefix(String args, Prefix firstPrefix) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, firstPrefix);

        if (!arePrefixesPresent(argMultimap, firstPrefix)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT, TaskSortCommand.MESSAGE_USAGE));
        }
        SortTypes sortTypes = SortTypes.parse(argMultimap.getValue(CliSyntax.PREFIX_SORT_TYPE).orElse("INVALID"));
        switch (sortTypes) {
        case NAME_ALPHABETICAL:
            return new ContactSortNameAlphabeticalCommand();
        case NAME_REVERSE_ALPHABETICAL:
            return new ContactSortNameReverseAlphabeticalCommand();
        case CHRONOLOGICAL_ADDED:
            return new ContactSortAddedChronologicalCommand();
        case PHONE_ASCENDING:
            return new ContactSortPhoneAscendingCommand();
        case PHONE_DESCENDING:
            return new ContactSortPhoneDescendingCommand();
        default:
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    ContactSortCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

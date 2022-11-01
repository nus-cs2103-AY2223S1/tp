package taskbook.logic.parser.contacts;

import java.util.stream.Stream;

import taskbook.commons.core.Messages;
import taskbook.commons.core.index.Index;
import taskbook.logic.commands.contacts.ContactDeleteCommand;
import taskbook.logic.parser.ArgumentMultimap;
import taskbook.logic.parser.ArgumentTokenizer;
import taskbook.logic.parser.CliSyntax;
import taskbook.logic.parser.Parser;
import taskbook.logic.parser.ParserUtil;
import taskbook.logic.parser.Prefix;
import taskbook.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ContactDeleteCommand object
 */
public class ContactDeleteCommandParser implements Parser<ContactDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ContactDeleteCommand
     * and returns a ContactDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ContactDeleteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_INDEX);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    ContactDeleteCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(argMultimap.getValue(CliSyntax.PREFIX_INDEX).get());

        return new ContactDeleteCommand(index);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

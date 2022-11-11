package paymelah.logic.parser;

import static paymelah.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static paymelah.logic.parser.CliSyntax.PREFIX_ABOVE;
import static paymelah.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static paymelah.logic.parser.CliSyntax.PREFIX_AFTER;
import static paymelah.logic.parser.CliSyntax.PREFIX_BEFORE;
import static paymelah.logic.parser.CliSyntax.PREFIX_BELOW;
import static paymelah.logic.parser.CliSyntax.PREFIX_DATE;
import static paymelah.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static paymelah.logic.parser.CliSyntax.PREFIX_MONEY;
import static paymelah.logic.parser.CliSyntax.PREFIX_NAME;
import static paymelah.logic.parser.CliSyntax.PREFIX_PHONE;
import static paymelah.logic.parser.CliSyntax.PREFIX_TAG;
import static paymelah.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static paymelah.logic.parser.CliSyntax.PREFIX_TIME;
import static paymelah.logic.parser.ParserUtil.argumentMultimapToDebtsDescriptor;
import static paymelah.logic.parser.ParserUtil.argumentMultimapToPersonDescriptor;

import paymelah.logic.commands.FindCommand;
import paymelah.logic.parser.ParserUtil.PersonDescriptor;
import paymelah.logic.parser.exceptions.ParseException;
import paymelah.model.person.PersonMatchesDescriptorPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * FindCommand and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_TELEGRAM, PREFIX_ADDRESS,
                        PREFIX_TAG, PREFIX_DESCRIPTION, PREFIX_MONEY, PREFIX_DATE, PREFIX_TIME,
                        PREFIX_ABOVE, PREFIX_BELOW, PREFIX_BEFORE, PREFIX_AFTER);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        PersonDescriptor personDescriptor = argumentMultimapToPersonDescriptor(argMultimap);
        FindCommand.DebtsDescriptor debtsDescriptor = argumentMultimapToDebtsDescriptor(argMultimap);

        if (!personDescriptor.isAnyFieldSet() && !debtsDescriptor.isAnyFieldSet()) {
            throw new ParseException(FindCommand.MESSAGE_NO_KEYWORDS);
        }

        return new FindCommand(new PersonMatchesDescriptorPredicate(personDescriptor, debtsDescriptor));
    }
}

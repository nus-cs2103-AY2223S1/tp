package paymelah.logic.parser;

import static paymelah.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static paymelah.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static paymelah.logic.parser.CliSyntax.PREFIX_NAME;
import static paymelah.logic.parser.CliSyntax.PREFIX_PHONE;
import static paymelah.logic.parser.CliSyntax.PREFIX_TAG;
import static paymelah.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static paymelah.logic.parser.ParserUtil.argumentMultimapToPersonDescriptor;

import paymelah.logic.commands.AddCommand;
import paymelah.logic.parser.ParserUtil.PersonDescriptor;
import paymelah.logic.parser.exceptions.ParseException;
import paymelah.model.person.Person;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_TELEGRAM,
                        PREFIX_ADDRESS, PREFIX_TAG);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        PersonDescriptor descriptor = argumentMultimapToPersonDescriptor(argMultimap);

        Person person = new Person(descriptor);

        return new AddCommand(person);
    }
}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENTMOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLANNEDMOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREVIOUSMOD;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.UserCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.CurrentModule;
import seedu.address.model.module.PlannedModule;
import seedu.address.model.module.PreviousModule;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.user.ExistingUser;
import seedu.address.model.person.user.User;

/**
 * Parses input arguments and creates a new UserCommand object
 */
public class UserCommandParser implements Parser<UserCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UserCommand
     * and returns an UserCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UserCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_CURRENTMOD, PREFIX_PREVIOUSMOD, PREFIX_PLANNEDMOD);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UserCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<CurrentModule> currList = ParserUtil.parseCurrentModules(argMultimap.getAllValues(PREFIX_CURRENTMOD));
        Set<PreviousModule> prevList = ParserUtil.parsePreviousModules(argMultimap.getAllValues(PREFIX_PREVIOUSMOD));
        Set<PlannedModule> plannedList = ParserUtil.parsePlannedModules(argMultimap.getAllValues(PREFIX_PLANNEDMOD));


        User user = new ExistingUser(name, phone, email, address, currList, prevList, plannedList);

        return new UserCommand(user);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

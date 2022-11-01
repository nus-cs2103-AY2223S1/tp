package coydir.logic.parser;

import static coydir.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static coydir.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static coydir.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static coydir.logic.parser.CliSyntax.PREFIX_EMAIL;
import static coydir.logic.parser.CliSyntax.PREFIX_LEAVE;
import static coydir.logic.parser.CliSyntax.PREFIX_NAME;
import static coydir.logic.parser.CliSyntax.PREFIX_PHONE;
import static coydir.logic.parser.CliSyntax.PREFIX_POSITION;
import static coydir.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import coydir.logic.commands.AddCommand;
import coydir.logic.parser.exceptions.ParseException;
import coydir.model.person.Address;
import coydir.model.person.Department;
import coydir.model.person.Email;
import coydir.model.person.EmployeeId;
import coydir.model.person.Name;
import coydir.model.person.Person;
import coydir.model.person.Phone;
import coydir.model.person.Position;
import coydir.model.person.Rating;
import coydir.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {
    private static final int DEFAULT_LEAVES = 14;
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_POSITION, PREFIX_DEPARTMENT, PREFIX_ADDRESS, PREFIX_LEAVE, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_POSITION, PREFIX_DEPARTMENT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        // Set mandatory fields
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Position position = ParserUtil.parsePosition(argMultimap.getValue(PREFIX_POSITION).get());
        Department department = ParserUtil.parseDepartment(argMultimap.getValue(PREFIX_DEPARTMENT).get());

        // Set optional fields as default/null values
        Phone phone = Phone.getNullPhone();
        Email email = Email.getNullEmail();
        Address address = Address.getNullAddress();
        Rating rating = Rating.getNullRating();
        Set<Tag> tagList = new HashSet<>();
        int numberOfLeaves = DEFAULT_LEAVES;

        // Set optional fields individually
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        }
        if (!argMultimap.getAllValues(PREFIX_TAG).isEmpty()) {
            tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        }
        if (!argMultimap.getAllValues(PREFIX_LEAVE).isEmpty()) {
            numberOfLeaves = Integer.valueOf(ParserUtil.parseNumber(argMultimap.getValue(PREFIX_LEAVE).get()));
        }

        EmployeeId employeeId = new EmployeeId();
        Person person = new Person(
                name, employeeId, phone, email, position, department, address, tagList, numberOfLeaves, rating
                );

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

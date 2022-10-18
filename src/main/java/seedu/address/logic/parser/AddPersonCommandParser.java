package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_ADDRESS_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_ADDRESS_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_EMAIL_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_EMAIL_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_PHONE_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_PHONE_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_TAG_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_TAG_STR_LONG;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddPersonCommand object
 */
public class AddPersonCommandParser implements Parser<AddPersonCommand> {
    private final JCommander parser;
    private final AddPersonCommandArguments arguments = new AddPersonCommandArguments();

    /**
     * Creates an AddPersonCommandParser with default options
     */
    public AddPersonCommandParser() {
        this.parser = JCommander.newBuilder().addObject(arguments).build();
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddPersonCommand
     * and returns an AddPersonCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddPersonCommand parse(String args) throws ParseException {
        try {
            List<String> argsList = new ArrayList<>(List.of(ArgumentTokenizer.tokenize(args)));
            if (argsList.size() == 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE));
            }

            parser.parse(argsList.toArray(String[]::new));

            Name name = ParserUtil.parseName(arguments.name);
            Phone phone = ParserUtil.parsePhone(arguments.phone);
            Email email = ParserUtil.parseEmail(arguments.email);
            Address address = ParserUtil.parseAddress(arguments.address);
            Set<Tag> tagList = arguments.tags != null
                ? ParserUtil.parseTags(arguments.tags)
                : Set.of();

            Person person = new Person(name, phone, email, address, tagList);
            return new AddPersonCommand(person);
        } catch (ParameterException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE), e);
        }
    }

    private static class AddPersonCommandArguments {
        @Parameter(names = {FLAG_NAME_STR, FLAG_NAME_STR_LONG}, required = true, description = "Name of person")
        private String name;

        @Parameter(names = {FLAG_PHONE_STR, FLAG_PHONE_STR_LONG}, required = true, description = "Phone of person")
        private String phone;

        @Parameter(names = {FLAG_EMAIL_STR, FLAG_EMAIL_STR_LONG}, required = true, description = "Email of person")
        private String email;

        @Parameter(names = {FLAG_ADDRESS_STR, FLAG_ADDRESS_STR_LONG}, required = true, description = "Address of "
            + "person")
        private String address;

        @Parameter(names = {FLAG_TAG_STR, FLAG_TAG_STR_LONG}, description = "Tags of person")
        private List<String> tags;
    }
}

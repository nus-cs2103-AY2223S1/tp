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

import java.util.Arrays;
import java.util.Set;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {
    private final Options options;

    /**
     * Creates an AddCommandParser with default options.
     */
    public AddCommandParser() {
        Options options = new Options();
        options.addRequiredOption(FLAG_NAME_STR, FLAG_NAME_STR_LONG, true, "Name of person");
        options.addRequiredOption(FLAG_PHONE_STR, FLAG_PHONE_STR_LONG, true, "Phone of person");
        options.addRequiredOption(FLAG_EMAIL_STR, FLAG_EMAIL_STR_LONG, true, "Email of person");
        options.addRequiredOption(FLAG_ADDRESS_STR, FLAG_ADDRESS_STR_LONG, true, "Address of person");
        options.addOption(FLAG_TAG_STR, FLAG_TAG_STR_LONG, true, "Tag of person");
        this.options = options;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        try {
            CommandLineParser parser = new DefaultParser();
            String[] argsArray = ArgumentTokenizer.tokenize(args);
            CommandLine cmd = parser.parse(options, argsArray);

            if (cmd.getArgs().length > 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
            }

            Name name = ParserUtil.parseName(cmd.getOptionValue(FLAG_NAME_STR));
            Phone phone = ParserUtil.parsePhone(cmd.getOptionValue(FLAG_PHONE_STR));
            Email email = ParserUtil.parseEmail(cmd.getOptionValue(FLAG_EMAIL_STR));
            Address address = ParserUtil.parseAddress(cmd.getOptionValue(FLAG_ADDRESS_STR));
            Set<Tag> tagList = cmd.hasOption(FLAG_TAG_STR)
                    ? ParserUtil.parseTags(Arrays.asList(cmd.getOptionValues(FLAG_TAG_STR)))
                    : Set.of();

            Person person = new Person(name, phone, email, address, tagList);
            return new AddCommand(person);
        } catch (MissingArgumentException e) {
            Option opt = e.getOption();
            switch (opt.getOpt()) {
            case FLAG_NAME_STR:
                throw new ParseException(Name.MESSAGE_CONSTRAINTS);
            case FLAG_PHONE_STR:
                throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
            case FLAG_EMAIL_STR:
                throw new ParseException(Email.MESSAGE_CONSTRAINTS);
            case FLAG_ADDRESS_STR:
                throw new ParseException(Address.MESSAGE_CONSTRAINTS);
            default:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
            }
        } catch (org.apache.commons.cli.ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
    }
}

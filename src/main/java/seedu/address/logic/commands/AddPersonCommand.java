package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
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

import java.util.Set;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressConverter;
import seedu.address.logic.parser.EmailConverter;
import seedu.address.logic.parser.NameConverter;
import seedu.address.logic.parser.PhoneConverter;
import seedu.address.logic.parser.TagsConverter;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Adds a person to the address book.
 */
@CommandLine.Command(name = "person")
public class AddPersonCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + FLAG_NAME_STR + " NAME "
            + FLAG_PHONE_STR + " PHONE "
            + FLAG_EMAIL_STR + " EMAIL "
            + FLAG_ADDRESS_STR + " ADDRESS "
            + FLAG_TAG_STR + " TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + FLAG_NAME_STR + " \"John Doe\" "
            + FLAG_PHONE_STR + " 98765432 "
            + FLAG_EMAIL_STR + " johnd@example.com "
            + FLAG_ADDRESS_STR + " \"311, Clementi Ave 2, #02-25\" "
            + FLAG_TAG_STR + " friends "
            + FLAG_TAG_STR + " owesMoney";
    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private Person toAdd;

    @CommandLine.Option(names = {FLAG_NAME_STR, FLAG_NAME_STR_LONG}, required = true, description = "Name of person",
            converter = NameConverter.class)
    private Name name;

    @CommandLine.Option(names = {FLAG_PHONE_STR, FLAG_PHONE_STR_LONG}, required = true, description = "Phone of "
            + "person", converter = PhoneConverter.class)
    private Phone phone;

    @CommandLine.Option(names = {FLAG_EMAIL_STR, FLAG_EMAIL_STR_LONG}, required = true, description = "Email of "
            + "person", converter = EmailConverter.class)
    private Email email;

    @CommandLine.Option(names = {FLAG_ADDRESS_STR, FLAG_ADDRESS_STR_LONG}, required = true, description = "Address of "
            + "person", converter = AddressConverter.class)
    private Address address;

    @CommandLine.Option(names = {FLAG_TAG_STR, FLAG_TAG_STR_LONG}, description = "Tags of person",
            parameterConsumer = TagsConverter.class)
    private Set<Tag> tags;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec spec;

    public AddPersonCommand() {
    }

    /**
     * Creates an AddPersonCommand to add the specified {@code Person}
     */
    public AddPersonCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person toAdd = new Person(name, phone, email, address, tags);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPersonCommand // instanceof handles nulls
                && toAdd.equals(((AddPersonCommand) other).toAdd));
    }

}

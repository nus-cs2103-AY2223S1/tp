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

import java.util.HashSet;
import java.util.Set;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
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
@CommandLine.Command(name = "person", aliases = {"p"})
public class AddPersonCommand extends Command {
    public static final String COMMAND_WORD = "add person";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + FLAG_NAME_STR + " NAME "
            + FLAG_PHONE_STR + " PHONE "
            + FLAG_EMAIL_STR + " EMAIL "
            + FLAG_ADDRESS_STR + " ADDRESS "
            + "[" + FLAG_TAG_STR + " TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + FLAG_NAME_STR + " \"John Doe\" "
            + FLAG_PHONE_STR + " 98765432 "
            + FLAG_EMAIL_STR + " johnd@example.com "
            + FLAG_ADDRESS_STR + " \"311, Clementi Ave 2, #02-25\" "
            + FLAG_TAG_STR + " UX Designer "
            + FLAG_TAG_STR + " CSS Expert";
    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the contacts list";

    @CommandLine.Option(names = {FLAG_NAME_STR, FLAG_NAME_STR_LONG}, required = true, description = "Name of person")
    private Name name;

    @CommandLine.Option(names = {FLAG_PHONE_STR, FLAG_PHONE_STR_LONG}, required = true, description = "Phone of person")
    private Phone phone;

    @CommandLine.Option(names = {FLAG_EMAIL_STR, FLAG_EMAIL_STR_LONG}, required = true, description = "Email of person")
    private Email email;

    @CommandLine.Option(names = {FLAG_ADDRESS_STR, FLAG_ADDRESS_STR_LONG}, required = true,
            description = "Address of person")
    private Address address;

    @CommandLine.Option(names = {FLAG_TAG_STR, FLAG_TAG_STR_LONG}, description = "Tags of person",
            parameterConsumer = TagsConverter.class, arity = "*")
    private Set<Tag> tags = new HashSet<>();

    public AddPersonCommand() {
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
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddPersonCommand)) {
            return false;
        }

        // state check
        AddPersonCommand o = (AddPersonCommand) other;
        return name.equals(o.name)
                && phone.equals(o.phone)
                && email.equals(o.email)
                && address.equals(o.address)
                && tags.equals(o.tags);
    }

}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.internship.InternshipId;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonId;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Adds a person to the address book.
 */
public class AddPersonCommand extends Command {

    public static final String COMMAND_WORD = "addp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Name name;
    private final Phone phone;
    private final Email email;
    private final InternshipId internshipId;
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddPersonCommand(Person person) {
        requireNonNull(person);
        this.name = person.getName();
        this.phone = person.getPhone();
        this.email = person.getEmail();
        this.address = person.getAddress();
        this.internshipId = person.getInternshipId();
        this.tags.addAll(person.getTags());
    }

    /**
     * Creates an AddCommand to add a {@code Person} with the specified
     * {@code name}, {@code phone}, {@code email}, {@code address},
     * {@code internshipId}, and {@code tags}.
     */
    public AddPersonCommand(
            Name name,
            Phone phone,
            Email email,
            Address address,
            InternshipId internshipId,
            Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.internshipId = internshipId;
        this.tags.addAll(tags);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person toAdd;

        if (model.findInternshipById(internshipId) == null) {
            toAdd = new Person(
                    new PersonId(model.getNextPersonId()),
                    name,
                    phone,
                    email,
                    address,
                    null,
                    tags
            );
        } else {
            toAdd = new Person(
                    new PersonId(model.getNextPersonId()),
                    name,
                    phone,
                    email,
                    address,
                    internshipId,
                    tags
            );
        }

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
                && name.equals(((AddPersonCommand) other).name)
                && phone.equals(((AddPersonCommand) other).phone)
                && email.equals(((AddPersonCommand) other).email)
                && address.equals(((AddPersonCommand) other).address)
                && internshipId != null && internshipId.equals(((AddPersonCommand) other).internshipId)
                && tags.equals(((AddPersonCommand) other).tags));
    }
}

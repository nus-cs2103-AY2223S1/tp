package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]..."
            + "[" + PREFIX_LOAN + "10]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_LOAN + "50";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        // Tag linking

        Set<Tag> toAddTagSet = new HashSet<>();
        ObservableList<Tag> addressBookTagList = model.getTagList();
        Set<Tag> referenceTagSet = toAdd.getTags();
        Set<Tag> tagSet = new HashSet<>(toAdd.getTags());

        for (Tag currentTag : addressBookTagList) {
            for (Tag toAddTag : referenceTagSet) {
                if (currentTag.isSameTag(toAddTag)) {
                    toAddTagSet.add(currentTag);
                    tagSet.remove(toAddTag);
                }
            }
        }

        toAddTagSet.addAll(tagSet);

        Person newToAddPerson = new Person(toAdd.getName(), toAdd.getPhone(), toAdd.getEmail(),
                toAdd.getAddress(), toAddTagSet, toAdd.getLoan());

        // Add person reference to tags and add tags to address book's unique tag list.
        for (Tag tag : toAddTagSet) {
            tag.addPerson(newToAddPerson);
            model.addTag(tag);
        }

        // Tag linking finished

        model.addPerson(newToAddPerson);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newToAddPerson));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}

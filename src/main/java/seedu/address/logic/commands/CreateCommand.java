package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NETWORTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class CreateCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "create";

    public static final String COMMAND_SHORTCUT = "c";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a person to the address book. "
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_NETWORTH + "NETWORTH] "
            + "[" + PREFIX_MEETING_TIME + "MEETING TIME] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_DESCRIPTION + "Likes drinking coffee "
            + PREFIX_NETWORTH + "$1423 "
            + PREFIX_MEETING_TIME + "15-12-2022-19:00 "
            + PREFIX_TAG + "Potential";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";
    public static final String MESSAGE_UNDO = "Person removed: %1$s";
    public static final String MESSAGE_REDO = "Person added back: %1$s";

    private final Person toAdd;

    /**
     * Creates an CreateCommand to add the specified {@code Person}
     */
    public CreateCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public CommandResult undo(Model model) {
        model.deletePerson(toAdd);
        return new CommandResult(String.format(MESSAGE_UNDO, toAdd));
    }

    @Override
    public CommandResult redo(Model model) {
        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_REDO, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateCommand // instanceof handles nulls
                && toAdd.equals(((CreateCommand) other).toAdd));
    }
}

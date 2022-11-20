package seedu.clinkedin.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_DEGREETAG;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_JOBTYPETAG;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_SKILLTAG;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_STATUS + "STATUS"
            + "[" + PREFIX_SKILLTAG + "SKILL_TAG] (if tag type not deleted) "
            + "[" + PREFIX_DEGREETAG + "DEGREE_TAG] (if tag type not deleted) "
            + "[" + PREFIX_JOBTYPETAG + "JOB_TYPE_TAG] (if tag type not deleted) "
            + "[<custom_tag_prefix>/OLD_TAG-NEW_TAG] "
            + "[" + PREFIX_NOTE + "NOTE] "
            + "[" + PREFIX_RATING + "RATING]...\n "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_SKILLTAG + "Java "
            + PREFIX_SKILLTAG + "Python "
            + PREFIX_STATUS + "Application Pending "
            + PREFIX_NOTE + "Strong at Java."
            + PREFIX_RATING + "4";

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

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}

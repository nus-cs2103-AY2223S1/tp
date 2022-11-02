package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUBUSERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.StringJoiner;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;

/**
 * Adds a Student to the address book.
 */
public class StudentCommand extends Command {
    public static final String COMMAND_WORD = "student";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_YEAR + "YEAR] "
            + PREFIX_MODULE_CODE + "MODULE_CODE... "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_GENDER + "GENDER "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_GITHUBUSERNAME + "GITHUB_USERNAME]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_YEAR + "1 "
            + PREFIX_MODULE_CODE + "CS4226 "
            + PREFIX_MODULE_CODE + "CS5242 "
            + PREFIX_MODULE_CODE + "CS1101S "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "JohnD@example.com "
            + PREFIX_GENDER + "M "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney "
            + PREFIX_LOCATION + "UTown Residences "
            + PREFIX_GITHUBUSERNAME + "johnnyd";

    public static final String MESSAGE_SUCCESS = "New Student added: %1$s";

    public static final String MESSAGE_DUPLICATE_PERSON = "This Student already exists in the address book";

    public static final String STUDENT_TEMPLATE = new StringJoiner(" ").add(COMMAND_WORD)
        .add(PREFIX_NAME.getPrefix())
        .add(PREFIX_MODULE_CODE.getPrefix())
        .add(PREFIX_PHONE.getPrefix())
        .add(PREFIX_EMAIL.getPrefix())
        .add(PREFIX_GENDER.getPrefix())
        .add(PREFIX_TAG.getPrefix())
        .add(PREFIX_LOCATION.getPrefix())
        .add(PREFIX_GITHUBUSERNAME.getPrefix())
        .add(PREFIX_YEAR.getPrefix())
        .toString();

    private final Person toAdd;

    /**
     * Creates a ProfCommand to add the specified {@code Student}
     */
    public StudentCommand(Student toAdd) {
        requireNonNull(toAdd);
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        model.updatePieChart();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof StudentCommand // instanceof handles nulls
            && toAdd.equals(((StudentCommand) other).toAdd));
    }
}

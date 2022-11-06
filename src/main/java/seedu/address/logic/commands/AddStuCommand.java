package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Adds a student to the student list.
 */
public class AddStuCommand extends Command {

    public static final String COMMAND_WORD = "addstu";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to the student list.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_TELEGRAM + "TELEGRAM_HANDLE "
            + PREFIX_EMAIL + "EMAIL "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_TELEGRAM + "@johndoe "
            + PREFIX_EMAIL + "johnd@example.com ";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the address book";

    private final Student toAdd;

    /**
     * Creates an AddStuCommand to add the specified {@code Student}
     */
    public AddStuCommand(Student student) {
        requireNonNull(student);
        toAdd = student;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasStudent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.addStudent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddStuCommand // instanceof handles nulls
                && toAdd.equals(((AddStuCommand) other).toAdd));
    }
}

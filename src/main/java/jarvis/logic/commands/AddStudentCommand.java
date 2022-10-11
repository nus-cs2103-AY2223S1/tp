package jarvis.logic.commands;

import static jarvis.logic.parser.CliSyntax.PREFIX_NAME;
import static java.util.Objects.requireNonNull;

import jarvis.logic.commands.exceptions.CommandException;
import jarvis.model.Model;
import jarvis.model.Student;

/**
 * Adds a student to the student book.
 */
public class AddStudentCommand extends Command {

    public static final String COMMAND_WORD = "addstudent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to JARVIS.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "John Doe";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in JARVIS";

    private final Student studentToAdd;

    /**
     * Creates an AddStudentCommand to add the specified {@code Student}
     */
    public AddStudentCommand(Student student) {
        requireNonNull(student);
        studentToAdd = student;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasStudent(studentToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.addStudent(studentToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, studentToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddStudentCommand // instanceof handles nulls
                && studentToAdd.equals(((AddStudentCommand) other).studentToAdd));
    }
}

package jarvis.logic.commands;

import static jarvis.logic.parser.CliSyntax.PREFIX_MATRIC_NUM;
import static jarvis.logic.parser.CliSyntax.PREFIX_NAME;
import static java.util.Objects.requireNonNull;

import jarvis.logic.commands.exceptions.CommandException;
import jarvis.model.Model;
import jarvis.model.Student;
import jarvis.model.exceptions.MaxStudentsExceededException;

/**
 * Adds a student to the student book.
 */
public class AddStudentCommand extends Command {

    public static final String COMMAND_WORD = "addstudent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to JARVIS.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME " + PREFIX_MATRIC_NUM + "MATRIC_NUM\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "John Doe " + PREFIX_MATRIC_NUM + "A0123459G";

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

        try {
            model.addStudent(studentToAdd);
        } catch (MaxStudentsExceededException e) {
            throw new CommandException(e.getMessage());
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, studentToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddStudentCommand // instanceof handles nulls
                && studentToAdd.equals(((AddStudentCommand) other).studentToAdd));
    }
}

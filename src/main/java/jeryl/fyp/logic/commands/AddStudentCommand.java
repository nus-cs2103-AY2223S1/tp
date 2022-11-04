package jeryl.fyp.logic.commands;

import static java.util.Objects.requireNonNull;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_EMAIL;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_TAG;

import jeryl.fyp.logic.commands.exceptions.CommandException;
import jeryl.fyp.model.Model;
import jeryl.fyp.model.student.Student;

/**
 * Adds a student to the FYP manager.
 */
public class AddStudentCommand extends Command {

    public static final String COMMAND_WORD = "add -s";

    public static final String ALTERNATIVE_COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to the FYP manager.\n"
            + "Parameters: "
            + PREFIX_STUDENT_ID + "STUDENT_ID "
            + PREFIX_STUDENT_NAME + "STUDENT_NAME "
            + PREFIX_PROJECT_NAME + "FYP_NAME "
            + PREFIX_EMAIL + "EMAIL "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "(STUDENT_ID should be in format  \"A\" + (7 digits) + (1 uppercase letter), e.g. A0123456G)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT_ID + "A0123456G "
            + PREFIX_STUDENT_NAME + "John Doe "
            + PREFIX_PROJECT_NAME + "Neural Network "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_TAG + "smart "
            + PREFIX_TAG + "isWorking";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the FYP manager";
    public static final String MESSAGE_EMAIL_USED = "This email is used by another student in the list already.";
    private final Student toAdd;

    /**
     * Creates an AddStudentCommand to add the specified {@code Student}
     */
    public AddStudentCommand(Student student) {
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
                || (other instanceof AddStudentCommand // instanceof handles nulls
                && toAdd.equals(((AddStudentCommand) other).toAdd));
    }

}

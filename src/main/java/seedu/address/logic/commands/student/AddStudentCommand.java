package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelType;
import seedu.address.model.student.Student;

/**
 * Adds a person to the address book.
 */
public class AddStudentCommand extends Command {

    public static final String COMMAND_WORD = "add student";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to the specified module.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ID + "STUDENT_ID "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_TELEGRAM + "TELEGRAM_HANDLE "
            + PREFIX_MODULE + "MODULE "
            + PREFIX_TUTORIAL + "TUTORIAL "
            + "[" + PREFIX_GRADE + "GRADE] "
            + "[" + PREFIX_ATTENDANCE + "ATTENDANCE] "
            + "[" + PREFIX_PARTICIPATION + "PARTICIPATION] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_ID + "A0232123X "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_TELEGRAM + "johnDoe "
            + PREFIX_MODULE + "CS2103T "
            + PREFIX_TUTORIAL + "W17 ";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Student toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddStudentCommand(Student student) {
        requireNonNull(student);
        toAdd = student;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), ModelType.STUDENT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddStudentCommand // instanceof handles nulls
                && toAdd.equals(((AddStudentCommand) other).toAdd));
    }
}

package friday.logic.commands;

import static friday.logic.parser.CliSyntax.PREFIX_CONSULTATION;
import static friday.logic.parser.CliSyntax.PREFIX_MASTERYCHECK;
import static friday.logic.parser.CliSyntax.PREFIX_NAME;
import static friday.logic.parser.CliSyntax.PREFIX_TELEGRAMHANDLE;
import static java.util.Objects.requireNonNull;

import friday.logic.commands.exceptions.CommandException;
import friday.model.Model;
import friday.model.student.Student;

/**
 * Adds a student to FRIDAY.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to FRIDAY. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_TELEGRAMHANDLE + "TELEGRAM HANDLE] "
            + "[" + PREFIX_CONSULTATION + "CONSULTATION] "
            + "[" + PREFIX_MASTERYCHECK + "MASTERY CHECK]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_TELEGRAMHANDLE + "johndoe "
            + PREFIX_CONSULTATION + "2022-09-01 "
            + PREFIX_MASTERYCHECK + "2022-08-24 ";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student's name or Telegram handle"
            + "already exists in FRIDAY";

    private final Student toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Student}
     */
    public AddCommand(Student student) {
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
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}

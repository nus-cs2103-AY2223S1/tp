package seedu.masslinkers.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.masslinkers.commons.core.Messages.MESSAGE_PHONE_WARNING;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_INTEREST;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import seedu.masslinkers.logic.commands.exceptions.CommandException;
import seedu.masslinkers.model.Model;
import seedu.masslinkers.model.student.Student;

/**
 * Adds a student to the mass linkers.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = "Add a batchmate's information in this manner: "
            +
            "\nadd " + PREFIX_NAME + "NAME "
            + PREFIX_TELEGRAM + "TELEGRAM "
            + "[" + PREFIX_GITHUB + "GITHUB] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_INTEREST + "INTEREST]...";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_SUCCESS_WARN = MESSAGE_PHONE_WARNING + MESSAGE_SUCCESS;
    public static final String MESSAGE_DUPLICATE_STUDENT = "The Telegram handle/"
            + "GitHub username/email/phone number already exist(s) in Mass Linkers.";

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
        String message = toAdd.hasIncorrectPhone() ? MESSAGE_SUCCESS_WARN : MESSAGE_SUCCESS;

        return new CommandResult(String.format(message, toAdd),
                false, false, true, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}

package seedu.trackascholar.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_SCHOLARSHIP;

import seedu.trackascholar.logic.commands.exceptions.CommandException;
import seedu.trackascholar.model.Model;
import seedu.trackascholar.model.applicant.Applicant;

/**
 * Adds an applicant to TrackAScholar.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an applicant to TrackAScholar. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_SCHOLARSHIP + "SCHOLARSHIP "
            + PREFIX_APPLICATION_STATUS + "APPLICATION STATUS "
            + "[" + PREFIX_MAJOR + "MAJOR]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_SCHOLARSHIP + "NUS Global Merit Scholarship "
            + PREFIX_APPLICATION_STATUS + "pending "
            + PREFIX_MAJOR + "Medicine "
            + PREFIX_MAJOR + "Computer Science";

    public static final String MESSAGE_SUCCESS = "New applicant added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPLICANT = "This applicant already exists in TrackAScholar";

    private final Applicant toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Applicant}.
     */
    public AddCommand(Applicant applicant) {
        requireNonNull(applicant);
        toAdd = applicant;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasApplicant(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICANT);
        }

        model.addApplicant(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}

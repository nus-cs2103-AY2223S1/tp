package seedu.intrack.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_WEBSITE;

import seedu.intrack.logic.commands.exceptions.CommandException;
import seedu.intrack.model.Model;
import seedu.intrack.model.internship.Internship;

/**
 * Adds an internship to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an internship to the tracker.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_POSITION + "POSITION "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_WEBSITE + "WEBSITE "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Google "
            + PREFIX_POSITION + "SWE "
            + PREFIX_PHONE + "65025300002 "
            + PREFIX_EMAIL + "support@google.com "
            + PREFIX_WEBSITE + "https://careers.google.com/ "
            + PREFIX_TAG + "Urgent";

    public static final String MESSAGE_SUCCESS = "New internship added: %1$s";

    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "This internship already exists in the tracker";

    private final Internship toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Internship}
     */
    public AddCommand(Internship internship) {
        requireNonNull(internship);
        toAdd = internship;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasInternship(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERNSHIP);
        }

        model.addInternship(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }

}

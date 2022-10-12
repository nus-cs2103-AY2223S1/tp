package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLIED_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.internship.Internship;

/**
 * Adds a internship to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an internship to the internship list.\n"
            + "Parameters: "
            + PREFIX_COMPANY + "COMPANY "
            + PREFIX_LINK + "LINK "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_APPLIED_DATE + "APPLIED_DATE "
            + "[" + PREFIX_TAG + "TAG]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COMPANY + "Tiktok "
            + PREFIX_LINK + "https://careers.tiktok.com/position/7132807469026117902/detail "
            + PREFIX_DESCRIPTION + "Global e-Commerce "
            + PREFIX_APPLIED_DATE + "11/10/2022 "
            + PREFIX_TAG + "Frontend";

    public static final String MESSAGE_SUCCESS = "New internship added: %1$s";
    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "This internship already exists in the address book";

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

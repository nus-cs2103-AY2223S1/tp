package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.staff.StaffNameContainsKeywordsPredicate;

/**
 * Finds a staff within the currently active project in HR Pro Max++.
 */
public class FindStaffCommand extends Command {

    public static final String COMMAND_WORD = "findstaff";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all staffs that are currently displayed "
            + "whose names contains parts of any specified keywords "
            + "(but can be case-insensitive) and displays\n"
            + "them as a list with index numbers.\n"
            + "A staff called Andy Lee can be found using these keywords: An, Lee, LEE\n"
            + "Parameters: "
            + "KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " "
            + "alice bob charlie ";

    private final StaffNameContainsKeywordsPredicate predicate;

    /**
     * Creates a FindStaffCommand with the specified {@code predicate}
     * to find the all staff within the currently active {@code project}.
     */
    public FindStaffCommand(StaffNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredStaffList(predicate);
        return (model.getFilteredStaffList().size() == 1)
                ? new CommandResult(String.format(Messages.MESSAGE_STAFFS_LISTED_SINGULAR_OVERVIEW,
                    model.getFilteredStaffList().size()))
                : new CommandResult(String.format(Messages.MESSAGE_STAFFS_LISTED_PLURAL_OVERVIEW,
                    model.getFilteredStaffList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof FindStaffCommand)
                && predicate.equals(((FindStaffCommand) other).predicate);
    }
}

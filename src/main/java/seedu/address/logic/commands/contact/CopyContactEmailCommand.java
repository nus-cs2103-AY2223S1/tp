package seedu.address.logic.commands.contact;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

/**
 * Lists all emails of people whose tags contain the argument keyword.
 * Keyword matching is case-sensitive.
 */
public class CopyContactEmailCommand extends Command {
    public static final String COMMAND_WORD = "copyC";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays emails of all persons whose label list contains "
            + "the specified label (case-sensitive) for easy copy-pasting.\n"
            + "Parameters: LABEL.\n"
            + "Example: " + COMMAND_WORD + " CS2103T";
    public static final String MESSAGE_COPIED_EMAILS = "Emails: %s";

    private final PersonContainsKeywordsPredicate predicate;

    public CopyContactEmailCommand(PersonContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);

        StringBuilder builder = new StringBuilder();
        List<Person> people = model.getFilteredPersonList();
        for (Person p : people) {
            builder.append(p.getEmail().toString() + " ");
        }

        return new CommandResult(
                String.format(MESSAGE_COPIED_EMAILS, builder.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CopyContactEmailCommand // instanceof handles nulls
                && predicate.equals(((CopyContactEmailCommand) other).predicate)); // state check
    }
}

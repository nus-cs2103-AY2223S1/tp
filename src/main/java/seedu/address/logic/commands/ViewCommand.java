package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameIsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Finds person in the addressbook whose name is specified by user for full detailed view of his data.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the person with the exact name "
            + "specified (case-insensitive) and displays his full details stored in the addressbook.\n"
            + "Parameters: KEYWORD [KEYWORD]...\n"
            + "Example: " + COMMAND_WORD + " alex han";

    private final NameIsKeywordsPredicate predicate;

    public ViewCommand(NameIsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        model.setFullView();
        if (model.getFilteredPersonList().isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_NAME_VIEW);
        }
        Person person = model.getFilteredPersonList().get(0);
        person.setFullView();

        return new CommandResult(String.format(Messages.MESSAGE_PERSON_FULL_VIEW, person.getName().fullName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ViewCommand
                && predicate.equals(((ViewCommand) other).predicate));
    }
}

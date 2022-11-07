package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonContainsSocial;

/**
 * Finds and lists all persons in uNivUSal whose social media contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class SocialCommand extends Command {

    public static final String COMMAND_WORD = "social";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons who has inputted social media"
            + " (case-insensitive).\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " tele";

    private final PersonContainsSocial predicate;

    /**
     * Filters the given list based on a person's preferred social
     *
     * @param predicate persons with the given preferred social
     */
    public SocialCommand(PersonContainsSocial predicate) {
        requireNonNull(predicate);

        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        UndoCommand.prepareSaveModelBefore(this, model);
        model.updateFilteredPersonList(predicate);
        UndoCommand.saveBeforeMod(model);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SocialCommand // instanceof handles nulls
                && predicate.equals(((SocialCommand) other).predicate)); // state check
    }
}
